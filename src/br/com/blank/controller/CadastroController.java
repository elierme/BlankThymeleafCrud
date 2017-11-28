package br.com.blank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.blank.model.Cliente;

@Controller
@RequestMapping(path = "/user/cadastro")
public class CadastroController {

	private int PAGE_SIZE = 5;

	@RequestMapping(path = "/listagem")
	public String listagem(HttpServletRequest request) {
		request.getSession().setAttribute("clientes", null);
		return "redirect:/user/cadastro/listagem/1";
	}

	@RequestMapping(path = "/listagem/{pageNumber}")
	public String listagem(@PathVariable Integer pageNumber, HttpServletRequest request, Model model) {
		PagedListHolder<Cliente> paged = (PagedListHolder<Cliente>) request.getSession().getAttribute("clientes");

		if (paged == null) {
			paged = new PagedListHolder<>(getClientes());
			paged.setPageSize(PAGE_SIZE);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage < paged.getPageCount() && goToPage >= 0)
				paged.setPage(goToPage);
		}

		request.getSession().setAttribute("clientes", paged);
		int current = paged.getPage() + 1;
		int begin = Math.max(1, current - PAGE_SIZE);
		int end = Math.min(begin + 5, paged.getPageCount());
		int totalPageCount = paged.getPageCount();

		model.addAttribute("currentIndex", current);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("totalPageCountIndex", totalPageCount);
	    model.addAttribute("baseUrl", "/user/cadastro/listagem/");
		model.addAttribute("paged", paged);
		
		

		return "/user/cadastro/listagem";
	}

	@RequestMapping(path = "/visualizar/{id}")
	public String visualizar(@PathVariable("id") String id, Model model) {
		model.addAttribute("cliente", getClientes().get(Integer.parseInt(id)));
		return "/user/cadastro/visualizar";
	}

	@RequestMapping(path = "/alterar/{id}")
	public String alterar(@PathVariable("id") String id, Model model) {
		model.addAttribute("cliente", getClientes().get(Integer.parseInt(id)));
		return "/user/cadastro/alterar";
	}

	@RequestMapping(path = "/alterar")
	public String salvar(@ModelAttribute Cliente cliente) {

		return "/user/cadastro/listagem";
	}

	@RequestMapping(path = "/incluir")
	public String incluir(@ModelAttribute Cliente cliente) {

		return "/user/cadastro/listagem";
	}

	private List<Cliente> getClientes() {
		List<Cliente> lista = new ArrayList<>();
		lista.add(new Cliente(0, "Bematech", "Bematech"));
		lista.add(new Cliente(1, "B2BSistemas", "B2BSistemas"));
		lista.add(new Cliente(2, "UNESA", "UNESA"));
		lista.add(new Cliente(3, "America", "America"));
		lista.add(new Cliente(4, "Booking", "Booking"));
		lista.add(new Cliente(5, "Tesla", "Tesla"));
		lista.add(new Cliente(6, "Philips", "Philips"));
		lista.add(new Cliente(7, "Hitachi", "Hitachi"));
		lista.add(new Cliente(8, "Sony", "Sony"));
		lista.add(new Cliente(9, "Sega", "Sega"));
		lista.add(new Cliente(10, "Microsof", "Microsof"));
		lista.add(new Cliente(11, "ANS", "ANS"));

		return lista;
	}
}
