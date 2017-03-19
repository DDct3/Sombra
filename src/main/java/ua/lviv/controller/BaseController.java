package ua.lviv.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;
import ua.lviv.entity.Basket;
import ua.lviv.entity.Commodity;
import ua.lviv.service.BasketService;
import ua.lviv.service.CommodityService;
import ua.lviv.service.UserService;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.font.MultipleMaster;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Pavlo on 04/03/2017.
 */

@Controller
public class BaseController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String homePage(Model model) {
        List<Commodity> commodities = commodityService.findAll();
        model.addAttribute("commodity", commodities);
        return "home";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    private String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    private String registration(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email, @RequestParam(value = "phone") String phone) {
        userService.add(login, password, email, phone);
        return "redirect:/";
    }

    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    private String loginpage() {
        return "login";
    }

    @RequestMapping(value = "/upgradeCommodity", method = RequestMethod.GET)
    private String upgradeCommodity(@RequestParam(value = "select") String type) {
        if (type.equalsIgnoreCase("Add new commodity")) {
            return "addNewCommodity";
        }
        if (type.equalsIgnoreCase("Edit commodity")) {
            return "editCommodity";
        } else {
            return "deleteCommodity";
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    private String findByName(@RequestParam(value = "find") String name, Model model) {
        if (name.equalsIgnoreCase("") || name == null) {
            List<Commodity> commodityList = commodityService.findAll();
            model.addAttribute("find", commodityList);
            return "redirect:/";
        } else {
            List<Commodity> commodityList = commodityService.findByName(name);
            model.addAttribute("find", commodityList);
            return "find";
        }
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    private String basket(Model model, Principal principal) {
        List<Basket> baskets = basketService.showAll(userService.findByLogin(principal.getName()).getId());
        model.addAttribute("basket", baskets);
        return "basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    private String basket(@RequestParam(value = "name") String name, @RequestParam(value = "price") Double price, HttpServletResponse response, Model model, Principal principal) {
        basketService.add(name, price, userService.findByLogin(principal.getName()));
        try {
            response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";

    }
    @RequestMapping(value = "/addBasket", method = RequestMethod.POST)
    private String addbasket(@RequestParam(value = "name") String name, @RequestParam(value = "price") Double price, HttpServletResponse response, Model model, Principal principal) {
        basketService.add(name, price, userService.findByLogin(principal.getName()));
        try {
            response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";

    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    private String removeCommodityInBasket(@RequestParam(value = "id") int id) {
        basketService.delete(id);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    private String write(@RequestParam(value = "name") String name, @RequestParam(value = "price") double price, @RequestParam(value = "id") int id){
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/future/IdeaProjects/Sombra/src/main/webapp/res/check.pdf"));

            if (bufferedReader.readLine().equals(" ") || bufferedReader.readLine().length() <= 1) {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/future/IdeaProjects/Sombra/src/main/webapp/res/check.pdf"));
                document.open();
                String check = " \n Name :" + name + "\n Price :" + price + "\n Data :" + new Date();
                Anchor anchor = new Anchor(check);
                anchor.setName("Check");
                Paragraph paragraph = new Paragraph();
                paragraph.setSpacingBefore(50);
                paragraph.add(anchor);
                document.add(paragraph);
                document.close();
                removeCommodityInBasket(id);
            }else {
                PdfReader pdfReader = new PdfReader("/home/future/IdeaProjects/Sombra/src/main/webapp/res/check.pdf");
                TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
                String text = PdfTextExtractor.getTextFromPage(pdfReader, 1, strategy);

                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/future/IdeaProjects/Sombra/src/main/webapp/res/check.pdf"));
                document.open();
                String check = "\n Name :" + name + "\n Price :" + price + "\n Data :" + new Date();
                Anchor anchor = new Anchor("***" + text + "\n" + check + "\n  \n ***");
                anchor.setName("Check");
                Paragraph paragraph = new Paragraph();
                paragraph.setSpacingBefore(50);
                paragraph.add(anchor);
                document.add(paragraph);
                document.close();
                removeCommodityInBasket(id);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/basket";
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    private String download(HttpServletResponse response) throws IOException, MessagingException {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition","attachment; filename = check.pdf");

        File file = new File("/home/future/IdeaProjects/Sombra/src/main/webapp/res/check.pdf");
        OutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);

        byte [] bytes = new byte[4096];
        int lenght;
        while ((lenght = fileInputStream.read(bytes)) > 0){
            outputStream.write(bytes, 0, lenght);
        }

        fileInputStream.close();
        outputStream.close();

        FileWriter fileWriter = new FileWriter("/home/future/IdeaProjects/Sombra/src/main/webapp/res/check.pdf");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(" ");
            bufferedWriter.close();
        return "redirect:/basket";
    }

    @RequestMapping(value = "/addNewCommodity", method = RequestMethod.POST)
    private String addNewCommodity(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "price") Double price,
                                   @RequestParam(value = "description") String description,
                                   @RequestParam(value = "select") String category,
                                   @RequestParam(value = "image")MultipartFile image) throws IOException, SQLException {

        byte[] bytes = image.getBytes();
        commodityService.add(name, price, category, description, bytes);
        return "redirect:/";
    }

    @RequestMapping(value = "/editCommodity", method = RequestMethod.POST)
    private String editCommodity(@RequestParam(value = "id") int id,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "price") Double price,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value = "select") String category,
                                 @RequestParam(value = "image")MultipartFile image) throws IOException, SQLException {

        byte[] bytes = image.getBytes();
        commodityService.edit(id, name, price, category, description, bytes);
        return "redirect:/";
    }

    @RequestMapping(value = "/deleteCommodity", method = RequestMethod.POST)
    private String deleteCommodity(@RequestParam(value = "id") int id) {
        commodityService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    private String findTelephone(@PathVariable String name, Model model) {
        if (name.equalsIgnoreCase("Telephone")) {
            List<Commodity> commodities = commodityService.findByCategory(name);
            model.addAttribute("category", commodities);
            return "findCategory";
        }
        if (name.equalsIgnoreCase("Tablet")) {
            List<Commodity> commodities = commodityService.findByCategory(name);
            model.addAttribute("category", commodities);
            return "findCategory";
        }
        if (name.equalsIgnoreCase("Accessories")) {
            List<Commodity> commodities = commodityService.findByCategory(name);
            model.addAttribute("category", commodities);
            return "findCategory";
        } else {
            List<Commodity> commodities = commodityService.findByCategory(name);
            model.addAttribute("category", commodities);
            return "findCategory";
        }
    }

    @RequestMapping(value = "/commodity/{id}")
    private String commodityPage(@PathVariable Integer id, Model model) throws IOException {
        Commodity commodity = commodityService.findById(id);
        model.addAttribute("commodity", commodity);
        return "commodity";
    }


    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam(value = "id") Integer id, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {

        Commodity commodity = commodityService.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(commodity.getImage());

        response.getOutputStream().close();

    }
}