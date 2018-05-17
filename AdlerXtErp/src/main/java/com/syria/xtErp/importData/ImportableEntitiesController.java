package com.syria.xtErp.importData;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/entities")
public class ImportableEntitiesController {
	
	@RequestMapping( method = RequestMethod.GET)

	public List<ImportableEntities> getEntities() {
		List<ImportableEntities> res = Arrays.asList(ImportableEntities.values());
		return res;
	}

}
