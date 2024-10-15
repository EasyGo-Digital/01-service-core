package eu.easygoit.service.impl;

import eu.easygoit.annotation.CodeGenLocal;
import eu.easygoit.annotation.SrvRepo;
import eu.easygoit.com.rest.service.impl.CodifiableService;
import eu.easygoit.config.JwtProperties;
import eu.easygoit.constants.DomainConstants;
import eu.easygoit.enums.IEnumAppToken;
import eu.easygoit.model.AppNextCode;
import eu.easygoit.model.TokenConfig;
import eu.easygoit.model.schema.SchemaColumnConstantName;
import eu.easygoit.repository.TokenConfigRepository;
import eu.easygoit.service.ITokenConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The type Token config service.
 */
@Service
@Transactional
@CodeGenLocal(value = NextCodeService.class)
@SrvRepo(value = TokenConfigRepository.class)
public class TokenConfigService extends CodifiableService<Long, TokenConfig, TokenConfigRepository> implements ITokenConfigService {

    private final JwtProperties jwtProperties;

    @Autowired
    private TokenConfigRepository tokenConfigRepository;

    /**
     * Instantiates a new Token config service.
     *
     * @param jwtProperties the jwt properties
     */
    public TokenConfigService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    @Override
    public TokenConfig buildTokenConfig(String domain, IEnumAppToken.Types tokenType) {
        //Serach for token config configured for the domein by type
        Optional<TokenConfig> optional = tokenConfigRepository.findByDomainIgnoreCaseAndTokenType(domain, tokenType);
        if (!optional.isPresent()) {
            //Serach for token config configured for default by type
            optional = tokenConfigRepository.findByDomainIgnoreCaseAndTokenType(DomainConstants.DEFAULT_DOMAIN_NAME, tokenType);
        }

        if (optional.isPresent()) {
            return optional.get();
        }

        //Build token config secified by system properties
        return TokenConfig.builder()
                .issuer(domain)
                .audience(domain)
                .signatureAlgorithm(jwtProperties.getSignatureAlgorithm().name())
                .secretKey(jwtProperties.getSecretKey())
                .lifeTimeInMs(jwtProperties.getLifeTimeInMs())
                .build();
    }

    @Override
    public AppNextCode initCodeGenerator() {
        return AppNextCode.builder()
                .domain(DomainConstants.DEFAULT_DOMAIN_NAME)
                .entity(TokenConfig.class.getSimpleName())
                .attribute(SchemaColumnConstantName.C_CODE)
                .prefix("TKN")
                .valueLength(6L)
                .value(1L)
                .increment(1)
                .build();
    }
}
