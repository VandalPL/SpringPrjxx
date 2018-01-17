package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.controllers.commands.FoodFilter;
import edu.uph.ii.platformy.models.Cart;
import edu.uph.ii.platformy.models.Food;
import edu.uph.ii.platformy.services.CartService;
import edu.uph.ii.platformy.services.FoodService;

import edu.uph.ii.platformy.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller

@SessionAttributes(names={"USER"})
@Log4j2
public class FoodController {
	@Autowired
	private FoodService foodService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;




	@RequestMapping(value = "/barForm.html", method = {RequestMethod.GET, RequestMethod.POST})
	public String showFoodList(Model model, Pageable pageable, FoodFilter filter) {
		model.addAttribute("barForm", foodService.getAllFood(filter,pageable));
		return "bar";
	}


	//@ModelAttribute("USER")
	@GetMapping(path = "/bar.html", params = {"id_food"})
	public String Cart(Model model, long id_food, Pageable pagable, FoodFilter filter) {
		//vehicleService.deleteVehicle(did);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Long id = userService.getUserID(currentPrincipalName);

		Cart cart = new Cart(id_food, id);

		//// zapis wykorzystac metode na podstawie save Vehicle
		cartService.saveCart(cart);

	//	model.addAttribute("currentUserID", id);
		model.addAttribute("barForm", foodService.getAllFood(filter,pagable));
		//model.addAttribute(foodService.getAllFood(nic));

		return "/bar";
		//String queryString = prepareQueryString(request.getQueryString());
		//return String.format("redirect:bar.html");//robimy przekierowanie, ale zachowując parametry pageingu
	}

//    @Secured("ROLE_ADMIN")
//    @GetMapping("/room/delete/{id}")
//    public String deleteVehicle(long id, HttpServletRequest request){
//        roomService.deleteRoom(id);
//        //String queryString = prepareQueryString(request.getQueryString());
//        //return String.format("redirect:vehicleList.html%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
//        return "redirect:/room/list";
//    }
	@Secured("ROLE_ADMIN")
	@GetMapping(path = "/bar.html",params = {"did"})
	public String deleteFood( long did){
		foodService.deleteFood(did);
		//String queryString = prepareQueryString(request.getQueryString());
		return "confirmation";	}


//	@Secured("ROLE_ADMIN")
//	@PostMapping(value="/bar.html")
//	//@ResponseStatus(HttpStatus.CREATED)
//	public String EditAdd(@Valid @ModelAttribute("food") Food v, BindingResult errors){
//
//		if(errors.hasErrors()){
//			return "bar";
//		}
//
//		//	log.info("Data utworzenia komponentu "+v.getCreatedDate());
//		//	log.info("Data edycji komponentu "+new Date());
//
//		foodService.saveFood(v);
//
//		return "redirect:bar.html";//po udanym dodaniu/edycji przekierowujemy na listę
//	}



}

