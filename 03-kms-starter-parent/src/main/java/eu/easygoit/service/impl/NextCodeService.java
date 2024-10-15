package eu.easygoit.service.impl;

import eu.easygoit.model.AppNextCode;
import eu.easygoit.repository.AppNextCodeRepository;
import eu.easygoit.repository.NextCodeRepository;
import eu.easygoit.service.AbstractNextCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Next code service.
 */
@Slf4j
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