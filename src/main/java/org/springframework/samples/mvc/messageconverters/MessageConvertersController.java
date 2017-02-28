package org.springframework.samples.mvc.messageconverters;

import javax.validation.Valid;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;

@RestController
@RequestMapping("/messageconverters")
public class MessageConvertersController {

	// StringHttpMessageConverter

	@RequestMapping(value="/string", method=RequestMethod.POST)
	public String readString(@RequestBody String string) {
		return "Read string '" + string + "'";
	}

	@RequestMapping(value="/string", method=RequestMethod.GET)
	public String writeString() {
		return "Wrote a string";
	}

	// Form encoded data (application/x-www-form-urlencoded)

	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String readForm(@ModelAttribute JavaBean bean) {
		return "Read x-www-form-urlencoded: " + bean;
	}

	@RequestMapping(value="/form", method=RequestMethod.GET)
	public MultiValueMap<String, String> writeForm() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("foo", "bar");
		map.add("fruit", "apple");
		return map;
	}

	// Jaxb2RootElementHttpMessageConverter (requires JAXB2 on the classpath - useful for serving clients that expect to work with XML)

	@RequestMapping(value="/xml", method=RequestMethod.POST)
	public String readXml(@RequestBody JavaBean bean) {
		return "Read from XML: " + bean;
	}

	@RequestMapping(value="/xml", method=RequestMethod.GET)
	public JavaBean writeXml() {
		return new JavaBean("bar", "apple");
	}

	// MappingJacksonHttpMessageConverter (requires Jackson on the classpath - particularly useful for serving JavaScript clients that expect to work with JSON)

	@RequestMapping(value="/json", method=RequestMethod.POST)
	public String readJson(@Valid @RequestBody JavaBean bean) {
		return "Read from JSON: " + bean;
	}

	@RequestMapping(value="/json", method=RequestMethod.GET)
	public JavaBean writeJson() {
		return new JavaBean("bar", "apple");
	}

	// AtomFeedHttpMessageConverter (requires Rome on the classpath - useful for serving Atom feeds)

	@RequestMapping(value="/atom", method=RequestMethod.POST)
	public String readFeed(@RequestBody Feed feed) {
		return "Read " + feed.getTitle();
	}

	@RequestMapping(value="/atom", method=RequestMethod.GET)
	public @ResponseBody Feed writeFeed() {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("My Atom feed");
		return feed;
	}

	// RssChannelHttpMessageConverter (requires Rome on the classpath - useful for serving RSS feeds)

	@RequestMapping(value="/rss", method=RequestMethod.POST)
	public @ResponseBody String readChannel(@RequestBody Channel channel) {
		return "Read " + channel.getTitle();
	}

	@RequestMapping(value="/rss", method=RequestMethod.GET)
	public @ResponseBody Channel writeChannel() {
		Channel channel = new Channel();
		channel.setFeedType("rss_2.0");
		channel.setTitle("My RSS feed");
		channel.setDescription("Description");
		channel.setLink("http://localhost:8080/mvc-showcase/rss");
		return channel;
	}

}