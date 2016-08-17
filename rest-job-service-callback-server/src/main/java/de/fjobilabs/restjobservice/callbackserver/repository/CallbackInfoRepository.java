package de.fjobilabs.restjobservice.callbackserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.fjobilabs.restjobservice.callbackserver.domain.CallbackInfo;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 21:07:35
 * @version 1.0
 */
public interface CallbackInfoRepository extends MongoRepository<CallbackInfo, String> {
}
