package eu.easygoit.service.impl;

import eu.easygoit.encrypt.generator.ResizableKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Random key generator.
 */
@Slf4j
@Service
@Transactional
public class RandomKeyGenerator extends ResizableKeyGenerator {

}
