package adler.syria.XtErp.Entities;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import adler.syria.XtErp.Entities.Repositories.ClientRepository;

@ManagedBean
public class EntityPersistantManager {

	private final ClientRepository clientRepository;

	@Autowired
	public EntityPersistantManager(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public void persist(Object obj) {
		Client client = (Client) obj;
		clientRepository.save(client);
	}
}
