package eu.easygoit.service.impl;

import eu.easygoit.constants.DomainConstants;
import eu.easygoit.model.DigestConfig;
import eu.easygoit.model.PEBConfig;
import eu.easygoit.repository.DigesterConfigRepository;
import eu.easygoit.repository.PEBConfigRepository;
import eu.easygoit.service.ICryptoService;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.digest.PooledStringDigester;
import org.jasypt.digest.StringDigester;
import org.jasypt.digest.config.SimpleStringDigesterConfig;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The type Crypto service.
 */
@Slf4j
@Service
@Transactional
public class CryptoService implements ICryptoService {

    @Autowired
    private PEBConfigRepository pebConfigRepository;

    @Autowired
    private DigesterConfigRepository digesterConfigRepository;

    private StringEncryptor pebStringEncryptor(PEBConfig pebConfig) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(pebConfig.getPassword());
        config.setAlgorithm(pebConfig.getAlgorithm().name());
        config.setKeyObtentionIterations(pebConfig.getKeyObtentionIterations());
        config.setPoolSize(pebConfig.getPoolSize());
        config.setProviderName(pebConfig.getProviderName());
        config.setProviderClassName(pebConfig.getProviderClassName());
        config.setSaltGeneratorClassName("org.jasypt.iv." + pebConfig.getSaltGenerator().name());
        config.setStringOutputType(pebConfig.getStringOutputType().name());
        encryptor.setConfig(config);
        return encryptor;
    }

    private StringDigester digestStringEncryptor(DigestConfig digestConfig) {
        PooledStringDigester encryptor = new PooledStringDigester();
        SimpleStringDigesterConfig config = new SimpleStringDigesterConfig();
        config.setAlgorithm(digestConfig.getAlgorithm().name().replace("_", "-"));
        config.setIterations(digestConfig.getIterations());
        config.setSaltSizeBytes(digestConfig.getSaltSizeBytes());
        config.setSaltGeneratorClassName("org.jasypt.iv." + digestConfig.getSaltGenerator().name());
        config.setProviderName(digestConfig.getProviderName());
        config.setProviderClassName(digestConfig.getProviderClassName());
        config.setInvertPositionOfSaltInMessageBeforeDigesting(digestConfig.getInvertPositionOfSaltInMessageBeforeDigesting());
        config.setInvertPositionOfPlainSaltInEncryptionResults(digestConfig.getInvertPositionOfPlainSaltInEncryptionResults());
        config.setUseLenientSaltSizeCheck(digestConfig.getUseLenientSaltSizeCheck());
        config.setPoolSize(digestConfig.getPoolSize());
        config.setStringOutputType(digestConfig.getStringOutputType().name());
        encryptor.setConfig(config);
        return encryptor;
    }

    private PasswordEncryptor passwordEncryptor(DigestConfig digestConfig) {
        ConfigurablePasswordEncryptor encryptor = new ConfigurablePasswordEncryptor();
        SimpleStringDigesterConfig config = new SimpleStringDigesterConfig();
        config.setAlgorithm(digestConfig.getAlgorithm().name().replace("_", "-"));
        config.setIterations(digestConfig.getIterations());
        config.setSaltSizeBytes(digestConfig.getSaltSizeBytes());
        config.setSaltGeneratorClassName("org.jasypt.iv." + digestConfig.getSaltGenerator().name());
        config.setProviderName(digestConfig.getProviderName());
        config.setProviderClassName(digestConfig.getProviderClassName());
        config.setInvertPositionOfSaltInMessageBeforeDigesting(digestConfig.getInvertPositionOfSaltInMessageBeforeDigesting());
        config.setInvertPositionOfPlainSaltInEncryptionResults(digestConfig.getInvertPositionOfPlainSaltInEncryptionResults());
        config.setUseLenientSaltSizeCheck(digestConfig.getUseLenientSaltSizeCheck());
        config.setPoolSize(digestConfig.getPoolSize());
        config.setStringOutputType(digestConfig.getStringOutputType().name());
        encryptor.setConfig(config);
        return encryptor;
    }

    @Override
    public StringEncryptor getPebEncryptor(String domain) {
        Optional<PEBConfig> optional = pebConfigRepository.findFirstByDomainIgnoreCase(domain);
        if (!optional.isPresent()) {
            optional = pebConfigRepository.findFirstByDomainIgnoreCase(DomainConstants.DEFAULT_DOMAIN_NAME);
        }

        if (optional.isPresent()) {
            return this.pebStringEncryptor(optional.get());
        }

        log.warn("peb config not found with domain {}", domain);
        return stringEncryptorDefault();
    }

    @Override
    public StringDigester getDigestEncryptor(String domain) {
        Optional<DigestConfig> optional = digesterConfigRepository.findFirstByDomainIgnoreCase(domain);
        if (!optional.isPresent()) {
            optional = digesterConfigRepository.findFirstByDomainIgnoreCase(DomainConstants.DEFAULT_DOMAIN_NAME);
        }

        if (optional.isPresent()) {
            return this.digestStringEncryptor(optional.get());
        }

        log.warn("digest config not found with domain {}", domain);
        return stringDigesterDefault();
    }

    @Override
    public PasswordEncryptor getPasswordEncryptor(String domain) {
        Optional<DigestConfig> optional = digesterConfigRepository.findFirstByDomainIgnoreCase(domain);
        if (!optional.isPresent()) {
            optional = digesterConfigRepository.findFirstByDomainIgnoreCase(DomainConstants.DEFAULT_DOMAIN_NAME);
        }

        if (optional.isPresent()) {
            return this.passwordEncryptor(optional.get());
        }

        log.warn("password config not found with domain {}", domain);
        return passwordEncryptorDefault();
    }

    private StringEncryptor stringEncryptorDefault() {
        Optional<PEBConfig> optional = pebConfigRepository.findFirstByDomainIgnoreCase(DomainConstants.DEFAULT_DOMAIN_NAME);
        if (optional.isPresent()) {
            return this.pebStringEncryptor(optional.get());
        }

        log.warn("No default peb config found");
        return new PooledPBEStringEncryptor();
    }

    private StringDigester stringDigesterDefault() {
        Optional<DigestConfig> optional = digesterConfigRepository.findFirstByDomainIgnoreCase(DomainConstants.DEFAULT_DOMAIN_NAME);
        if (optional.isPresent()) {
            return this.digestStringEncryptor(optional.get());
        }

        log.warn("No default digest config found");
        return new PooledStringDigester();
    }

    private PasswordEncryptor passwordEncryptorDefault() {
        Optional<DigestConfig> optional = digesterConfigRepository.findFirstByDomainIgnoreCase(DomainConstants.DEFAULT_DOMAIN_NAME);
        if (optional.isPresent()) {
            return this.passwordEncryptor(optional.get());
        }

        log.warn("No default digest config found");
        return new StrongPasswordEncryptor();
    }
}
