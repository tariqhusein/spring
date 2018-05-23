package adler.syria.XtErp.Entities;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ManagedBean;
import org.apache.commons.beanutils.PropertyUtils;
import adler.syria.XtErp.ImportProcess.ImportableEntities;

@ManagedBean
public class EntityManager {

	private Map<ImportableEntities, Class<?>> entities;

	public EntityManager() {
		super();
		entities = new HashMap<ImportableEntities, Class<?>>();
		entities.put(ImportableEntities.Client, Client.class);
	}

	public Object populatetWithValues(ImportableEntities importableEntity, Map<String, Object> values) {

		Object obj = null;
		try {
			obj = entities.get(importableEntity).newInstance();
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				try {
					PropertyUtils.setProperty(obj, entry.getKey(), entry.getValue());
				} catch (InvocationTargetException | NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
