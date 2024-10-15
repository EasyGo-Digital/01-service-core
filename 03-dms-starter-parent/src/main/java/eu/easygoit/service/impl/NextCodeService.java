package eu.easygoit.service.impl;

import eu.easygoit.model.AppNextCode;
import eu.easygoit.repository.AppNextCodeRepository;
import eu.easygoit.repository.NextCodeRepository;
import eu.easygoit.service.AbstractNextCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Next code service.
 */
@Service
@Transactional
public class NextCodeService extends AbstractNextCodeService<AppNextCode> {

    @Autowired
    private AppNextCodeRepository nextCodeRepository;

    @Override
    public NextCodeRepository nextCodeRepository() {
        return nextCodeRepository;
    }
}