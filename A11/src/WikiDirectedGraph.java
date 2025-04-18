import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * WikiDirectedGraph dynamically explores the Wikipedia article graph.
 * It extends DirectedGraph<URI> and overrides getNeighbors and traversal bounds.
 * For this file, you need to add the Jsoup library to your project if it
 * is not already included.
 * <p>
 * Go to project structure => modules => search for jsoup 1.19.1 and add it
 */
public class WikiDirectedGraph extends DirectedGraph<URI> {

    private static final String BASE_URL = "https://en.wikipedia.org";

    private final @NotNull Map<URI, Set<URI>> cache;
    private final @NotNull Random             random;
    private final int                         politeDelayMs;
    private final int                         maxVisited;

    public WikiDirectedGraph(int politeDelayMs, int maxVisited) {
        this.cache = new HashMap<>();
        this.random = new Random();
        this.politeDelayMs = politeDelayMs;
        this.maxVisited = maxVisited;
    }

    public @NotNull URI toURI(@NotNull String title) {
        return URI.create(BASE_URL + "/wiki/" + title.replace(" ", "_"));
    }

    public @NotNull String fromURI(@NotNull URI uri) {
        String[] parts = uri.getPath().split("/");
        return parts.length >= 3 ? parts[2].replace("_", " ") : uri.toString();
    }

    @Override
    public @NotNull Set<URI> getNeighbors(@NotNull URI page) {
        if (cache.containsKey(page)) return cache.get(page);
        Set<URI> links = new HashSet<>();
        try {
            Thread.sleep(politeDelayMs + random.nextInt(politeDelayMs));
            Document doc = Jsoup.connect(page.toString()).userAgent("Mozilla").get();
            URI normalized = new URI(doc.location());

            Elements anchors = doc.select("#bodyContent a[href^='/wiki/']");
            for (Element a : anchors) {
                String href = a.attr("href");
                if (href.contains(":")) continue; // skip meta pages
                if (href.contains("#")) continue; // skip in-page anchors
                URI link = new URI(BASE_URL + href);
                links.add(link);
            }
            cache.put(normalized, links);
            return links;
        } catch (IOException | URISyntaxException | InterruptedException e) {
            return Set.of();
        }
    }

    @Override
    public void traverse(@NotNull URI start, @NotNull TraversalStrategy<URI> strategy) {
        strategy.start(start);
        int visited = 0;
        while (strategy.hasNext() && visited < maxVisited) {
            URI current = strategy.next();
            Set<URI> neighbors = getNeighbors(current);
            if (strategy.visit(current, neighbors).stop()) break;
            visited++;
        }
    }
}
