package adler.syria.XtErp.Entities;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.apache.commons.beanutils.PropertyUtils;

import adler.syria.XtErp.ImportProcess.ImportableEntities;

@ManagedBean
public class EntityManager {

	private final Map<ImportableEntities, Class<?>> entities;

	public EntityManager() {
		super();
		entities = new LinkedHashMap<ImportableEntities, Class<?>>();
		entities.put(ImportableEntities.Client, Client.class);
		/*
		 * try { Client obj = (Client)
		 * entities.get(ImportableEntities.Client).newInstance(); // List<Field> fields
		 * =Arrays.asList( obj.getClass().getDeclaredFields()); // Iterator<Field>
		 * iterator = fields.iterator(); // while(iterator.hasNext()) { // Field field =
		 * iterator.next(); // field.set(obj, "TT"); // }
		 * 
		 * try { PropertyUtils.setProperty(obj, "description", "tt");
		 * 
		 * } catch (InvocationTargetException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (NoSuchMethodException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } } catch
		 * (InstantiationException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IllegalAccessException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public Object populatetWithValues(ImportableEntities importableEntity, Map<String, Object> values) {
		Object obj = null;
		try {
			obj = entities.get(importableEntity).newInstance();
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				try {
					PropertyUtils.setSimpleProperty(obj, entry.getKey(), entry.getValue());
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
