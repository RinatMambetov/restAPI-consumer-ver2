package dev.rinat.RestAPIConsumer2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Joke {
    private String type;
    private String setup;
    private String punchline;
    private int id;
}
