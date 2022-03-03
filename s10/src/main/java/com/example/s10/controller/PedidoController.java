package com.example.s10.controller;

import com.example.s10.dto.NovoPedidoDto;
import com.example.s10.model.PedidoModel;
import com.example.s10.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

        private PedidoService service;

        public PedidoController(PedidoService service){
            this.service = service;
        }

        @GetMapping
        public String listaPedidos(Model model){
            Iterable<PedidoModel> pedido = service.listaPedido();
            model.addAttribute("pedido", pedido);
            return "pedido";
        }

        @GetMapping("/formulario")
        public String formulario(NovoPedidoDto novoPedidoDto){
            return "pedido/formulario";
        }

        @PostMapping("/novo")
        public String novoPedido(@Valid NovoPedidoDto novoPedidoDto, BindingResult result){
            System.out.println("Binding Result: " + result);
            System.out.println("Pedido: " + novoPedidoDto);
            if (result.hasErrors()) {
                return "pedido/formulario";
            }
            service.adicionaPedido(novoPedidoDto.converter());
            return "redirect:/pedido";
        }
}
