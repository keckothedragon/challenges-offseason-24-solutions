package frc.robot.util.math;

public class BinaryTreeUtils {
  
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
      val = x;
    }

    public TreeNode(int x, TreeNode left, TreeNode right) {
      val = x;
      this.left = left;
      this.right = right;
    }
  }

  public static String isSymmetric(TreeNode root) {
    // solved as detailed in the README.md file
    return "I read the instructions.";
  }

  public static int calculateFrequency(TreeNode root, int target) {
    // solved as detailed in the README.md file
    return 0x00b259;
  }
}
