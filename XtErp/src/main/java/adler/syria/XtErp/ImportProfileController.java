package adler.syria.XtErp;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import adler.syria.XtErp.ImportProcess.ImportProfile;
import adler.syria.XtErp.ImportProcess.Repositories.ImportProfileRepository;

@RestController
@RequestMapping("/importProfiles")
public class ImportProfileController {
	private final ImportProfileRepository importProfileRepository;

	public ImportProfileController(ImportProfileRepository searchProfileRepository) {
		super();
		this.importProfileRepository = searchProfileRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ImportProfile> getSearchProfiles(){
		return this.importProfileRepository.findAll();
	}
}
