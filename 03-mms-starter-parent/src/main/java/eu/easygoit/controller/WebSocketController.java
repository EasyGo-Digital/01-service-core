package eu.easygoit.controller;

import eu.easygoit.annotation.CtrlHandler;
import eu.easygoit.api.WebSocketControllerApi;
import eu.easygoit.com.rest.controller.ResponseFactory;
import eu.easygoit.com.rest.controller.impl.ControllerExceptionHandler;
import eu.easygoit.dto.common.RequestContextDto;
import eu.easygoit.dto.wsocket.WsMessageWrapperDto;
import eu.easygoit.exception.handler.MmsExceptionHandler;
import eu.easygoit.service.IWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Web socket controller.
 */
@Slf4j
@Validated
@RestController
@CtrlHandler(MmsExceptionHandler.class)
@RequestMapping(path = "/api/v1/private/ws")
public class WebSocketController extends ControllerExceptionHandler implements WebSocketControllerApi {

    @Autowired
    private IWebSocketService webSocketService;

    @Override
    public ResponseEntity<?> sendMessageToUser(RequestContextDto requestContext,
                                               Long recieverId, WsMessageWrapperDto message) {
        try {
            webSocketService.saveAndSendToUser(recieverId, message);
            return ResponseFactory.ResponseOk();
        } catch (Exception ex) {
            return getBackExceptionResponse(ex);
        }
    }

    @Override
    public ResponseEntity<?> sendMessageToGroup(RequestContextDto requestContext,
                                                Long groupId, WsMessageWrapperDto message) {
        try {
            webSocketService.saveAndSendToGroup(groupId, message);
            return ResponseFactory.ResponseOk();
        } catch (Exception ex) {
            return getBackExceptionResponse(ex);
        }
    }

    @Override
    public ResponseEntity<?> sendMessageToAll(RequestContextDto requestContext,
                                              WsMessageWrapperDto message) {
        try {
            webSocketService.saveAndSendToAll(message);
            return ResponseFactory.ResponseOk();
        } catch (Exception ex) {
            return getBackExceptionResponse(ex);
        }
    }
}
