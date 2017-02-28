package org.springframework.samples.mvc.mapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

	@RequestMapping("/mapping/path")
	public String byPath() {
		return "Mapped by path!";
	}

	@RequestMapping(value="/mapping/path/*", method=RequestMethod.GET)
	public String byPathPattern(HttpServletRequest request) {
		return "Mapped by path pattern ('" + request.getRequestURI() + "')";
	}

	@RequestMapping(value="/mapping/method", method=RequestMethod.GET)
	public String byMethod() {
		return "Mapped by path + method";
	}

	@RequestMapping(value="/mapping/parameter", method=RequestMethod.GET, params="foo")
	public String byParameter() {
		return "Mapped by path + method + presence of query parameter!";
	}

	@RequestMapping(value="/mapping/parameter", method=RequestMethod.GET, params="!foo")
	public String byParameterNegation() {
		return "Mapped by path + method + not presence of query parameter!";
	}

	@RequestMapping(value="/mapping/header", method=RequestMethod.GET, headers="FooHeader=foo")
	public String byHeader() {
		return "Mapped by path + method + presence of header!";
	}

	@RequestMapping(value="/mapping/header", method=RequestMethod.GET, headers="!FooHeader")
	public String byHeaderNegation() {
		return "Mapped by path + method + absence of header!";
	}

	@RequestMapping(value="/mapping/consumes", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String byConsumes(@RequestBody JavaBean javaBean) {
		return "Mapped by path + method + consumable media type (javaBean '" + javaBean + "')";
	}

	@RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public JavaBean byProducesJson() {
		return new JavaBean();
	}

	@RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
	public JavaBean byProducesXml() {
		return new JavaBean();
	}

}
