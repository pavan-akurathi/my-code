package demo.web;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.entity.Contact;
import demo.repository.ContactRepository;

@Component
@Path("/contacts")
public class ContactRestController {
  
  @Autowired
  private ContactRepository repo;
  
  @GET  
  @Produces("application/json")
  public List<Contact> getAll() {
    return repo.findAll();
  }
  /*
  @RequestMapping(method=RequestMethod.POST)
  public Contact create(@RequestBody Contact contact) {
    return repo.save(contact);
  }
  
  @RequestMapping(method=RequestMethod.DELETE, value="{id}")
  public void delete(@PathVariable String id) {
    repo.delete(id);
  }
  
  @RequestMapping(method=RequestMethod.PUT, value="{id}")
  public Contact update(@PathVariable String id, @RequestBody Contact contact) {
    Contact update = repo.findOne(id);
    update.setAddress(contact.getAddress());
    update.setEmail(contact.getEmail());
    update.setFacebookProfile(contact.getFacebookProfile());
    update.setFirstName(contact.getFirstName());
    update.setGooglePlusProfile(contact.getGooglePlusProfile());
    update.setLastName(contact.getLastName());
    update.setLinkedInProfile(contact.getLinkedInProfile());
    update.setPhoneNumber(contact.getPhoneNumber());
    update.setTwitterHandle(contact.getTwitterHandle());
    return repo.save(update);
  }*/

}