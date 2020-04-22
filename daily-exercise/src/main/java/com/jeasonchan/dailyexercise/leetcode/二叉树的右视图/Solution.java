package com.jeasonchan.dailyexercise.leetcode.二叉树的右视图;


/*
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

从题目可以看出，一定是个满二叉树，使用BFS遍历每一层的节点，从右往左数第一个即为看到的值


 */

import org.springframework.stereotype.Component;

import java.util.*;

@SuppressWarnings("unchecked")
@Component("Solution199")
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (null == root) {
            return new ArrayList<Integer>() {
                {
                    this.add(null);
                }
            };

        }


        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        LinkedHashMap<TreeNode, Integer> record = new LinkedHashMap<>();
        record.put(root, 0);

        while (!queue.isEmpty()) {
            TreeNode topNode = queue.poll();
            int distanceFromRoot = record.get(topNode);
            queue.add(topNode.right);
            queue.add(topNode.left);
            record.put(topNode.right, distanceFromRoot + 1);
            record.put(topNode.left, distanceFromRoot + 1);
        }

        int currentDistance = 0;
        List<Integer> result = new ArrayList<>();

        for (Map.Entry<TreeNode, Integer> entry : record.entrySet()) {
            if (currentDistance == entry.getValue()) {
                if (result.size() == currentDistance) {
                    result.add(entry.getKey().val);
                    currentDistance++;
                }
            }
        }

        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
