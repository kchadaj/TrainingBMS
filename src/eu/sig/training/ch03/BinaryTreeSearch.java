package eu.sig.training.ch03;

public class BinaryTreeSearch {

    // tag::calculateDepth[]
    public static int calculateDepth(BinaryTreeNode<Integer> node, int nodeValue) {
        if (node == null) {
            throw new IllegalArgumentException("A reference to argument 'node' was not specified");
        }
        int depth = 0;
        if (node.getValue() == nodeValue) {
            return depth;
        } else {
            if (nodeValue < node.getValue()) {
                BinaryTreeNode<Integer> left = node.getLeft();
                return internalCalculateDepth(left, nodeValue);
            } else {
                BinaryTreeNode<Integer> right = node.getRight();
                return internalCalculateDepth(right, nodeValue);
            }
        }
    }
    // end::calculateDepth[]

    private static int internalCalculateDepth(BinaryTreeNode<Integer> node, int nodeValue) {
        if (node == null) {
            throw new TreeException("Value not found in tree!");
        } else {
            return 1 + calculateDepth(node, nodeValue);
        }
    }

}
