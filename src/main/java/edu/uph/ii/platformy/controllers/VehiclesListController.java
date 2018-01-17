package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.models.Food;
import edu.uph.ii.platformy.services.FoodService;
import edu.uph.ii.platformy.services.VehicleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;

@Controller
@SessionAttributes("searchCommand")
@Log4j2
public class VehiclesListController{
	@Autowired
	private VehicleService vehicleService;




	@Secured("IS_AUTHENTICATED_FULLY")
	@RequestMapping(value="/vehicleList.html", params = "id", method = RequestMethod.GET)
	public String showVehicleDetails(Model model, Long id){
		log.info("Pokazywanie szczegółów");
		model.addAttribute("vehicle", vehicleService.getVehicle(id));
		return "vehicleDetails";
	}

	@ModelAttribute("searchCommand")
	public VehicleFilter getSimpleSearch(){
		return new VehicleFilter();
	}

	@GetMapping(value="/vehicleList.html", params = {"all"})
	public String resetehicleList(@ModelAttribute("searchCommand") VehicleFilter search){
		search.clear();
		return "redirect:vehicleList.html";
	}

	@RequestMapping(value="/vehicleList.html", method = {RequestMethod.GET, RequestMethod.POST})
	public String showVehicleList(Model model, @ModelAttribute("searchCommand") VehicleFilter search, Pageable pageable){
		model.addAttribute("vehicleListPage", vehicleService.getAllVehicles(search, pageable));
		return "vehicleList";
	}




	@Secured("ROLE_ADMIN")
	@GetMapping(path="/vehicleList.html", params={"did"})
	public String deleteVehicle(long did, HttpServletRequest request){
		vehicleService.deleteVehicle(did);
		String queryString = prepareQueryString(request.getQueryString());
		return String.format("redirect:vehicleList.html?%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
	}

	private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
		return queryString.substring(queryString.indexOf("&")+1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości
		DecimalFormat numberFormat = new DecimalFormat("#0.00");
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);
		numberFormat.setGroupingUsed(false);
		CustomNumberEditor priceEditor = new CustomNumberEditor(Float.class, numberFormat, true);
		binder.registerCustomEditor(Float.class, "minPrice", priceEditor);
		binder.registerCustomEditor(Float.class, "maxPrice", priceEditor);

	}
}




