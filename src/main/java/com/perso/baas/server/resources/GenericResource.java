package com.perso.baas.server.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Inject;
import com.googlecode.objectify.Key;
import com.perso.baas.server.dao.ObjectifyGenericDao;
import com.perso.baas.shared.BaasPojo;

/**
 * 
 *
 * @param <K> La clef de l'objet P
 * @param <P> L'objet manipulé par le Dao et la ressource
 * @param <D> Le Dao avec lequel interagit la ressource
 */
public abstract class GenericResource<K, P extends BaasPojo<K>, D extends ObjectifyGenericDao<P>> {
	
	protected D dao;

	@Inject
	public GenericResource(D dao) {
		this.dao = dao;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public P create(P pojo) {
		Key<P> key = dao.put(pojo);
		pojo.setKey(generateKey(key));
		return pojo;
	}

	@GET
	@Path("/{id}")
	public P retrieve(@PathParam("id") String id) throws EntityNotFoundException {
		if(getKeyClassName() != null && getKeyClassName().equals(Long.class.getCanonicalName())){
			return dao.get(Long.decode(id));
		}
		return dao.get(id);
	}

	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public P update(P pojo) {
		Key<P> key = dao.put(pojo);
		if(pojo.getKey() == null){
			pojo.setKey(generateKey(key));
		}
		return pojo;
	}
	
	@DELETE
	@Path("/{id}")
	public Boolean delete(@PathParam("id") String id) {
		return dao.delete(Long.decode(id));
	}
	
	@GET
	@Path("/list")
	public List<P> list(){
		return dao.list();
	}
	
	/**
	 * @param objectifyKey 
	 * @return the key of my object
	 */
	protected abstract K generateKey(Key<P>  objectifyKey);
	
	protected abstract String getKeyClassName();

	public void setDao(D dao) {
		this.dao = dao;
	}
}
