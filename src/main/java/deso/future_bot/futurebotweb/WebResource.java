package deso.future_bot.futurebotweb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import deso.future_bot.futurebotweb.model.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping()
public class WebResource {

    @Autowired
    private RequestApi requestApi;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping({""})
    public String showIndex(@NotNull ModelMap model, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        var jwt = getToken(request, response);
        return getHome(model, getCache("userName", request), jwt);
    }

    String getHome(@NotNull ModelMap model, String userName, String jwt) throws JsonProcessingException {
        model.put("name", userName);
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
            var errors = new StringBuilder("");
            double currentPosition = 0.0;
            double currentValue = 0.0;
            double pnl = 0.0;

            for (StrategyInfo info : informationList) {
                currentPosition += info.getCurrentPosition();
                currentValue += info.getCurrentValue();
                pnl += info.getPnl();
                if (!info.getErrors().isEmpty()) {
                    errors.append(info.getErrors());
                    errors.append("\n");
                }
            }
            summary.setCurrentPosition(currentPosition);
            summary.setCurrentValue(currentValue);
            summary.setCurrentLevel(currentPosition / currentValue);
            summary.setPnl(pnl);
            model.addAttribute("summary", summary);
            model.put("error", errors.toString());
        }
        return "welcome";
    }

    @GetMapping({"/login"})
    public String showLoginPage() {
        return "login";
    }

    @GetMapping({"register"})
    public String register() {
        return "register";
    }

    @PostMapping({"/login"})
    public String login(ModelMap model,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "username") @NotNull String username,
                        @RequestParam(value = "password") @NotNull String password) throws JsonProcessingException {

        var loginVM = new LoginVM();
        loginVM.setUsername(username);
        loginVM.setPassword(password);
        var call = requestApi.call("/authenticate", HttpMethod.POST, loginVM);
        if (call.getStatusCode() != HttpStatus.OK) {
            model.put("error", "Unauthorized");
            return "login";
        }
        var jwt = objectMapper.readValue(call.getBody(), JWTToken.class).getIdToken();
        var cookie = new Cookie("jwt", jwt);
        var nameCookie = new Cookie("userName", username);
        response.addCookie(cookie);
        response.addCookie(nameCookie);
        return getHome(model, username, jwt);
    }

    @GetMapping({"/strategy"})
    public String getStrategy(ModelMap model,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        var jwt = getToken(request, response);
        if (jwt == null || jwt.length() == 0) {
            return "login";
        }
        try {
            var call = requestApi.get("/strategies", jwt);
            if (call.getStatusCode() != HttpStatus.OK) {
                model.put("error", "Unauthorized");
                return "redirect:/login";
            }
            model.addAttribute("strategies", objectMapper.readValue(call.getBody(), new TypeReference<List<StrategyResponse>>() {
            }));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("title", "Strategy");
        return "strategy";
    }

    @GetMapping({"/information"})
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
        var titleSummary = new StringBuilder();
        if (!informationList.isEmpty()) {
            var summary = new StrategyInfo();
            double currentPosition = 0.0;
            double currentValue = 0.0;
            double pnl = 0.0;

            for (StrategyInfo info : informationList) {
                currentPosition += info.getCurrentPosition();
                currentValue += info.getCurrentValue();
                pnl += info.getPnl();
                var token = info.getPair().replace("USDT", "");
                if (!titleSummary.toString().contains(token)) {
                    if (!titleSummary.isEmpty()) {
                        titleSummary.append("/");
                    }
                    titleSummary
                            .append(token)
                            .append(" ")
                            .append(Formatter.formatDecimal(info.getMarkPrice(), 1));
                }
            }
            summary.setCurrentPosition(currentPosition);
            summary.setCurrentValue(currentValue);
            summary.setCurrentLevel(currentPosition / currentValue);
            summary.setPnl(pnl);
            model.addAttribute("summary", summary);
            titleSummary.append(", ").append(Formatter.formatDecimal(pnl, 0)).append(" USDT");
        }
        model.addAttribute("informations", informationList);
        if (titleSummary.isEmpty()) {
            titleSummary.append("Information");
        }
        model.addAttribute("title", titleSummary);
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
        model.addAttribute("title", "Strategy");
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
        model.addAttribute("title", "Strategy");
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
        model.addAttribute("title", "Strategy");
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
        model.addAttribute("title", "User");
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
        model.addAttribute("title", "Strategy");
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
        model.addAttribute("title", "Strategy");
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

