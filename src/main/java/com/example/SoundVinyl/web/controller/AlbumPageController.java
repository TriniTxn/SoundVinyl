package com.example.SoundVinyl.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/albums")
public class AlbumPageController {

    public String albumsPage() {
        return "albums";
    }
}
