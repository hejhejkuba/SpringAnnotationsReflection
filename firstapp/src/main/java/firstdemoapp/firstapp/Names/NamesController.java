package firstdemoapp.firstapp.Names;

import firstdemoapp.firstapp.Names.Names;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Parameter;

@Controller
public class NamesController
{
    private Names names;
    private NamesService namesService;
    @Autowired
    public NamesController(NamesService namesService)
    {
        this.namesService = new NamesService();
        this.names = new Names();
    }


    @GetMapping(value = "/")
    public String main(Model model)
    {
    return "welcome"; //view
    }

    @PostMapping(value = "/postaction")
    public String submit_handle(@RequestParam String fname, Model model)
    {
        String message_value = fname;
        if (fname.toLowerCase().equals("hal"))
        {
            message_value = names.getHal();
            model.addAttribute("message", message_value);
            return namesService.PostName("hal");
        }
        if (fname.toLowerCase().equals("david"))
        {
            message_value = names.getDavid();
            model.addAttribute("message", message_value);
            return namesService.PostName("david");
        }
        if(fname.toLowerCase().equals("johny")) {

            throw new TeapotException("I'm a teapot");
        }
        model.addAttribute("message", message_value);
        return "Default";
    }
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public class TeapotException extends RuntimeException {
        public TeapotException(String message) {
            super(message, null, false, false);
        }
    }
}