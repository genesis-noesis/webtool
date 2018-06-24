package com.dev.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.dev.domain.SMS;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 */
@Transactional
public interface SMSDao extends CrudRepository<SMS, Long>, JpaSpecificationExecutor<SMS> {

  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param email the user email.
   */
  public SMS findByMessageID(String messageID);
  public List<SMS> findBySentToKannel(boolean sentToKannel, org.springframework.data.domain.Pageable pageable);

} 