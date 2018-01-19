package com.oguzhanuzman.github.gardirop.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FooRestController {

    @GetMapping("/foo")
    public Foo getFoo() {
        return new Foo("test");
    }

    class Foo {
        private String bar;

        public Foo(String bar) {
            this.bar = bar;
        }

        public String getBar() {
            return bar;
        }

        public void setBar(String bar) {
            this.bar = bar;
        }
    }
}
