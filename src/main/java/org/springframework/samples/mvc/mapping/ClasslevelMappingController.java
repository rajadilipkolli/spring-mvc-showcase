package org.springframework.samples.mvc.mapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class-mapping/*")
public class ClasslevelMappingController {

	@RequestMapping("/path")
	public String byPath() {
		return "Mapped by path!";
	}

	@RequestMapping(value="/path/*", method=RequestMethod.GET)
	public String byPathPattern(HttpServletRequest request) {
		return "Mapped by path pattern ('" + request.getRequestURI() + "')";
	}

	@RequestMapping(value="/method", method=RequestMethod.GET)
	public String byMethod() {
		return "Mapped by path + method";
	}

	@RequestMapping(value="/parameter", method=RequestMethod.GET, params="foo")
	public String byParameter() {
		return "Mapped by path + method + presence of query parameter!";
	}

	@RequestMapping(value="/parameter", method=RequestMethod.GET, params="!foo")
	public String byParameterNegation() {
		return "Mapped by path + method + not presence of query!";
	}

	@RequestMapping(value="/header", method=RequestMethod.GET, headers="FooHeader=foo")
	public String byHeader() {
		return "Mapped by path + method + presence of header!";
	}

	@RequestMapping(value="/notheader", method=RequestMethod.GET, headers="!FooHeader")
	public String byHeaderNegation() {
		return "Mapped by path + method + absence of header!";
	}


	@RequestMapping(value="/consumes", method=RequestMethod.POST, consumes="application/json")
	public String byConsumes(@RequestBody JavaBean javaBean) {
		return "Mapped by path + method + consumable media type (javaBean '" + javaBean + "')";
	}

	@RequestMapping(value="/produces", method=RequestMethod.GET, produces="application/json")
	public JavaBean byProduces() {
		return new JavaBean();
	}

}
