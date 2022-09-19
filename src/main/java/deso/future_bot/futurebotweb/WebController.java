package deso.future_bot.futurebotweb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import deso.future_bot.futurebotweb.model.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WebController {

    private static RequestApi requestApi;

    static ObjectMapper objectMapper;

    public String getInformation(@NotNull ModelMap model,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws JsonProcessingException {
        var jwt = getToken(request, response);
        if (jwt == null || jwt.length() == 0) {
            return "login";
        }
        var call = requestApi.get("/strategies/info", jwt);
        if (call.getStatusCode() != HttpStatus.OK) {
            model.put("error", "Unauthorized");
            return "redirect:/login";
        }
        var informationList = objectMapper.readValue(call.getBody(), new TypeReference<List<StrategyInfo>>() {
        });
        if (!informationList.isEmpty()) {
            var summary = new StrategyInfo();
            double currentPosition = 0.0;
            double currentValue = 0.0;
            double pnl = 0.0;

            for (StrategyInfo info : informationList) {
                currentPosition += info.getCurrentPosition();
                currentValue += info.getCurrentValue();
                pnl += info.getPnl();
            }
            summary.setCurrentPosition(currentPosition);
            summary.setCurrentValue(currentValue);
            summary.setCurrentLevel(currentPosition / currentValue);
            summary.setPnl(pnl);
            model.addAttribute("summary", summary);
        }
        model.addAttribute("informations", informationList);
        return "information";
    }

    @GetMapping({"/logout"})
    public String logout(HttpServletResponse response) {

        var cookie = new Cookie("jwt", null);
        response.addCookie(cookie);

        return "redirect:/login";
    }

    @GetMapping({"/add-strategy"})
    public String addStrategy(@NotNull ModelMap model) {
        model.addAttribute("strategy", new Strategy());
        return "add-strategy";
    }

    @GetMapping({"/update-strategy"})
    public String updateStrategy(@RequestParam int id, @NotNull ModelMap model,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws JsonProcessingException {
        var jwt = getToken(request, response);
        if (jwt == null || jwt.length() == 0) {
            return "login";
        }
        var call = requestApi.get("/strategies/" + id, jwt);
        if (call.getStatusCode() != HttpStatus.OK) {
            model.put("error", "Unauthorized");
            return "redirect:/login";
        }
        model.addAttribute("strategy", objectMapper.readValue(call.getBody(), StrategyResponse.class));
        return "update-strategy";
    }

    @PostMapping({"/add-strategy"})
    public String addStrategy(@NotNull ModelMap model,
                              @ModelAttribute("strategy") Strategy strategy,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        var jwt = getToken(request, response);
        if (jwt == null || jwt.length() == 0) {
            return "login";
        }
        var call = requestApi.call("/strategies", HttpMethod.POST, strategy, jwt);
        if (call.getStatusCode() != HttpStatus.OK) {
            model.put("error", "Unauthorized");
            return "redirect:/login";
        } else {
            return "redirect:/strategy";
        }
    }

    @PostMapping({"/users"})
    public String addUser(@NotNull ModelMap model,
                          @ModelAttribute("user") AddUser user) {
        return "redirect:/login";
    }

    @PostMapping({"/update-strategy"})
    public String updateStrategy(@RequestParam int id, @NotNull ModelMap model,
                                 @ModelAttribute("strategy") Strategy strategy,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        var jwt = getToken(request, response);
        if (jwt == null || jwt.length() == 0) {
            return "login";
        }
        var call = requestApi.call("/strategies/" + id, HttpMethod.PUT, strategy, jwt);
        if (call.getStatusCode() != HttpStatus.OK) {
            model.put("error", "Unauthorized");
            return "redirect:/login";
        } else {
            return "redirect:/strategy";
        }
    }

    @GetMapping({"/delete-strategy"})
    public String deleteStrategy(@RequestParam int id, @NotNull ModelMap model,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        var jwt = getToken(request, response);
        if (jwt == null || jwt.length() == 0) {
            return "login";
        }
        var call = requestApi.callWithNoBody("/strategies/" + id + "/delete", HttpMethod.PUT, jwt);
        if (call.getStatusCode() != HttpStatus.OK) {
            model.put("error", "Unauthorized");
            return "redirect:/login";
        } else {
            return "redirect:/strategy";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    private String getToken(HttpServletRequest request, HttpServletResponse response) {
        var jwt = getCache("jwt", request);
        if (jwt == null || jwt.length() == 0) {
            var cookie = new Cookie("jwt", null);
            var nameCookie = new Cookie("userName", null);
            response.addCookie(cookie);
            response.addCookie(nameCookie);
        }
        return jwt;
    }


    private String getCache(String name, HttpServletRequest request) {
        var cookies = request.getCookies();
        String result = null;
        if (cookies != null && cookies.length > 0) {
            for (var cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    result = cookie.getValue();
                }
            }
        }
        return result;
    }
}

