package com.bootcamp.ejercicio7m6.controller;

import com.bootcamp.ejercicio7m6.model.ContactoDTO;
import com.bootcamp.ejercicio7m6.service.ContactoService;
import com.bootcamp.ejercicio7m6.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/contactos")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(final ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("contactos", contactoService.findAll());
        return "contacto/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("contacto") final ContactoDTO contactoDTO) {
        return "contacto/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("contacto") @Valid final ContactoDTO contactoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contacto/add";
        }
        contactoService.create(contactoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("contacto.create.success"));
        return "redirect:/contactos";
    }

    @GetMapping("/edit/{idContacto}")
    public String edit(@PathVariable final Integer idContacto, final Model model) {
        model.addAttribute("contacto", contactoService.get(idContacto));
        return "contacto/edit";
    }

    @PostMapping("/edit/{idContacto}")
    public String edit(@PathVariable final Integer idContacto,
            @ModelAttribute("contacto") @Valid final ContactoDTO contactoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contacto/edit";
        }
        contactoService.update(idContacto, contactoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("contacto.update.success"));
        return "redirect:/contactos";
    }

    @PostMapping("/delete/{idContacto}")
    public String delete(@PathVariable final Integer idContacto,
            final RedirectAttributes redirectAttributes) {
        contactoService.delete(idContacto);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("contacto.delete.success"));
        return "redirect:/contactos";
    }

}
