package adler.syria.XtErp.Entities;

import javax.annotation.ManagedBean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import adler.syria.XtErp.Entities.Repositories.ClientRepository;

@ManagedBean
public class EntityPersistantManager {

	//private final ClientRepository clientRepository;
	@Autowired
	 private javax.persistence.EntityManager entityManager;

	/*@Autowired
	public EntityPersistantManager(ClientRepository clientRepository, SessionFactory sessionFactory) {
		this.clientRepository = clientRepository;
		this.sessionFactory = sessionFactory;
	}
*/
	public void persist(Object obj) {
		Client client = (Client) obj;
		//clientRepository.save(client);
		
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(client);
	}
}
