package hello;

import hello.search.FileSearchService;
import hello.storage.StorageFileNotFoundException;
import hello.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import Service.webInfo;
import Service.WebService;

import java.awt.Dialog.ModalExclusionType;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    private final FileSearchService fileSearchService;
    
    @Autowired
    WebService webService;
  

 
    @Autowired
    public FileUploadController(StorageService storageService, FileSearchService fileSearchService) {
        this.storageService = storageService;
        this.fileSearchService = fileSearchService;
    }
    
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String index(Model model){
    	webInfo index= webService.findById(6);
    	webInfo sidebar= webService.findById(8);
    	model.addAttribute("sidebar",sidebar);
    	model.addAttribute("index",index);
		return "tags/index";    	
    }

  //mapping page edit
    @RequestMapping("/edit")
    public String admin(Model model) throws IOException
    {
    	List<webInfo> webpages=webService.findAllWebPages();
    	model.addAttribute("webpages",webpages);
        return "jsp/Ckeditor";
    }
    
    
    //mapping page callpaper
    @RequestMapping(value = { "/callpaper" }, method = RequestMethod.GET)
    public String callpaper(Model model){
    
    	webInfo callpaper=webService.findById(1);
    	model.addAttribute("callpaper",callpaper);
		return "jsp/Callpaper";
    }
    //mapping page submission
    @RequestMapping(value = { "/submission" }, method = RequestMethod.GET)
    public String submission(Model model){
    	webInfo submission=webService.findById(2);
    	model.addAttribute("submission",submission);
		return "jsp/Submission";
    	
    	
    }
    //mapping page registation
    @RequestMapping(value = { "/registation" }, method = RequestMethod.GET)
    public String registation(Model model)
    {
    	
    	webInfo registration=webService.findById(3);
    	model.addAttribute("registration",registration);
		return "jsp/Registation";	
    }
    //mapping page keynotespeaker
    @RequestMapping(value = { "/keynotespeaker" }, method = RequestMethod.GET)
    public String keynotespeaker(Model model){
    	webInfo keynotespeaker=webService.findById(4);
    	model.addAttribute("keynotespeaker",keynotespeaker);
		return "jsp/Keynotespeaker";	
    }
    //mapping page contact
    @RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
    public String contact(Model model){
    	webInfo contact=webService.findById(7);
    	model.addAttribute("contact",contact);
		return "jsp/Contact";	
    }
    //mapping page home_page
    @RequestMapping(value = { "/home_page" }, method = RequestMethod.GET)
    public String homepage(Model model){
    	
		return "jsp/Home_page";	
    }
    //mapping page venuehotel
    @RequestMapping(value = { "/venuehotel" }, method = RequestMethod.GET)
    public String hotelmapping(Model model){
    	webInfo venuehotel=webService.findById(5);
    	model.addAttribute("venuehotel",venuehotel);
		return "jsp/Venue_Hotel";	
    }
    
    @RequestMapping(value = { "/latestnewsFirst" }, method = RequestMethod.GET)
    public String latestnewsFirst(Model model){
		return "jsp/last1";	
    }
    
    
    @RequestMapping(value = { "/latestnewsSecond" }, method = RequestMethod.GET)
    public String latestnewsSecond(Model model){
		return "jsp/last2";	
    }
    
    
    @RequestMapping(value = { "/latestnewsThird" }, method = RequestMethod.GET)
    public String latestnewsThird(Model model){
		return "jsp/last3";	
    }
    
    
	@RequestMapping(value = { "/delete-page-{pageID}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String pageID) {
		int id=Integer.parseInt(pageID);
		webService.deleteUserById(id);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = { "/new-webpage" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		webInfo webInfo = new webInfo();
		model.addAttribute("webInfo", webInfo);
		return "jsp/regist";
	}

	
	@RequestMapping(value = { "/new-webpage" }, method = RequestMethod.POST)
	public String saveUser(@Valid webInfo webInfo, BindingResult result,
			ModelMap model) 
	{
		webService.saveWebpage(webInfo);
		return "redirect:/";
	}
    
    
	@RequestMapping(value = { "/edit-webpage-{pageID}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String pageID, ModelMap model) {
		int id=Integer.parseInt(pageID);
		webInfo webInfo = webService.findById(id);
		model.addAttribute("webInfo", webInfo);
	
		return "jsp/regist";
	}
	
	@RequestMapping(value = { "/edit-webpage-{pageID}" }, method = RequestMethod.POST)
	public String updateUser(@Valid webInfo webInfo, 
			ModelMap model, @PathVariable int pageID) {

	

		webService.updateWebpage(webInfo);

		
		return "redirect:/";
	}

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
    
    @GetMapping("/search")
    public String searchFiles(@RequestParam("fileName") String name, Model model) throws IOException {
    	model.addAttribute("result",fileSearchService.searchFile(name));
        return "uploadForm";
    }
    
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
