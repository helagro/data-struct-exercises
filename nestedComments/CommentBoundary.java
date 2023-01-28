package nestedComments;

public class CommentBoundary {
    private final String startSymbol;
    private final String endSymbol;

    public CommentBoundary(String startSymbol, String endSymbol){
        this.startSymbol = startSymbol;
        this.endSymbol = endSymbol;
    }

    public boolean matchesStart(String input){
        return input.startsWith(startSymbol);
    }

    public boolean matchesEnd(String input){
        return input.startsWith(endSymbol);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommentBoundary)) return false;

        final CommentBoundary otherBoundary = (CommentBoundary) obj;
        return 
            startSymbol == otherBoundary.startSymbol &&
            endSymbol == otherBoundary.endSymbol;
    }
}
