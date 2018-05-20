package adler.syria.XtErp.ImportProcess.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import adler.syria.XtErp.ImportProcess.SearchProfile;
import adler.syria.XtErp.ImportProcess.Repositories.SearchProfileRepository;

@RestController
@RequestMapping("/searchProfiles")
public class SearchProfileController {
	private final SearchProfileRepository searchProfileRepository;

	public SearchProfileController(SearchProfileRepository searchProfileRepository) {
		super();
		this.searchProfileRepository = searchProfileRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<SearchProfile> getSearchProfiles(){
		return this.searchProfileRepository.findAll();
	}
}
