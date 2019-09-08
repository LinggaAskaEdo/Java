package com.callback.controller;

import com.callback.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Map;

@RestController
//@Controller
public class MyController
{
    private static final String ATTRIBUTE_MESSAGE = "message";

    @Value("${mymessage}")
    private String message;

    @GetMapping("/getMessage")
    public String getMessage(Model model)
    {
        model.addAttribute(ATTRIBUTE_MESSAGE, message);

        return "show";
    }

    @GetMapping("/getMessage2")
    public ModelAndView getMessage()
    {
        ModelAndView mav = new ModelAndView();

        mav.addObject(ATTRIBUTE_MESSAGE, message);
        mav.setViewName("show");

        return mav;
    }

    @GetMapping("/getMessageAndTime")
    public String getMessageAndTime(ModelMap map)
    {
        LocalDateTime ldt = LocalDateTime.now();

        DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        fmt.withLocale(new Locale("sk", "SK"));
        fmt.withZone(ZoneId.of("CET"));

        String time = fmt.format(ldt);

        map.addAttribute(ATTRIBUTE_MESSAGE, message).addAttribute("time", time);

        return "show";
    }

    @GetMapping("/testRedirect")
    public ModelAndView testRedirect()
    {
        return new ModelAndView("redirect:/testKaspro");
    }

    @GetMapping("/testKaspro")
    public ModelAndView testKaspro()
    {
        RestTemplate restTemplate = new RestTemplate();
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(10000);

        String html = restTemplate.postForObject("http://dev.kaspro.id/kaspro/setuppin", "{hasilEnkrip}", String.class);

        return new ModelAndView("doku", "url", html);

//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("{aaaa}");
//        modelAndView.setViewName("doku2");

//        return modelAndView;
    }

    @PostMapping(value = "/reg/ister")
    public ModelAndView registration(@RequestParam Map<String, String> params)
    {
        ModelAndView view = new ModelAndView();

        testMethod(view, params);

        return view;

//        return new ModelAndView("show", "message", params.get("message"));
    }

    private void testMethod(ModelAndView view, Map<String, String> params)
    {
        if (params.get("message").equalsIgnoreCase("A"))
        {
            view.addObject("message", "A");
            view.addObject("desc", "Huruf A");
            view.setViewName("show");
        }
        else if (params.get("message").equalsIgnoreCase("B"))
        {
            view.addObject("message", "B");
            view.addObject("desc", "Huruf B");
            view.setViewName("movie");
        }
        else
        {
            view.setViewName("redirect:/testKaspro");
        }

//        view.setViewName("show");
    }

    @PostMapping(value = "/reg/ister2")
    public ModelAndView registration2(@RequestBody Map<String, String> params)
    {
        ModelAndView view = new ModelAndView();
        view.addObject("message", params.get("message"));
        view.addObject("desc", params.get("desc"));
        view.setViewName("show");

        return view;
    }

    @PostMapping(value = "/reg/ister3")
    public ModelAndView registration3(@RequestBody Response response)
    {
        ModelAndView view = new ModelAndView();
        view.addObject("message", response.getMessage());
        view.addObject("desc", response.getDesc());
        view.setViewName("show");

        return view;
    }
}