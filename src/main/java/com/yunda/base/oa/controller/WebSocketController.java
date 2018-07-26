package com.yunda.base.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.yunda.base.system.service.SessionService;


@Controller
public class WebSocketController {
	@Autowired
	SimpMessagingTemplate template;

	@Autowired
    SessionService sessionService;

}