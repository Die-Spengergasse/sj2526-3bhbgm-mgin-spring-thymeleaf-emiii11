package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Pets;
import at.spengergasse.spring_thymeleaf.entities.PetsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetsController {

    private final PetsRepository petRepository;

    public PetsController(PetsRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/petlist")
    public String pets(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        return "petlist";
    }

    @GetMapping("/add")
    public String addPets(Model model) {
        model.addAttribute("pet", new Pets());
        return "add_pets";
    }

    @PostMapping("/add")
    public String addPets(@ModelAttribute("pet") Pets pet) {
        petRepository.save(pet);
        return "redirect:/pets/petlist";
    }

    @GetMapping("/delete/{id}")
    public String deletePet(@PathVariable("id") Long id) {
        petRepository.deleteById(id);
        return "redirect:/pets/petlist";
    }

    @GetMapping("/edit/{id}")
    public String editPet(@PathVariable("id") Long id, Model model) {
        Pets pet = petRepository.findById(id).orElseThrow();
        model.addAttribute("pet", pet);
        return "edit_pets";
    }

    @PostMapping("/edit")
    public String updatePet(@ModelAttribute("pet") Pets pet) {
        petRepository.save(pet);
        return "redirect:/pets/petlist";
    }
}