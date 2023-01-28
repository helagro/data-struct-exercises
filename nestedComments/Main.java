package nestedComments;

import java.util.Stack;

class Main{
    final static CommentBoundary[] commentBoundaries = {
        new CommentBoundary("(*", "*)"),
        new CommentBoundary("{-", "-}")
    };
    final static String input = "(* a comment {- with (* another comment *) inside *)";
    final static Stack<CommentBoundary> stack = new Stack<>();

    public static void main(String[] args) {
        for(int i = 0; i<input.length(); i++){
            final String subString = input.substring(i);
            for(final CommentBoundary commentBoundary : commentBoundaries){
                if(commentBoundary.matchesStart(subString)){
                    stack.push(commentBoundary);
                } else if (commentBoundary.matchesEnd(subString)){
                    final CommentBoundary lastItem = stack.pop();
                    if(lastItem != commentBoundary){
                        System.out.println("Invalid");
                        return;
                    }
                }
            }
        }

        if(stack.empty()){
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}