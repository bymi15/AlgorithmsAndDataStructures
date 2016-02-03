#include <iostream>

struct Node
{
	Node() {
		this->data = -1;
		this->left = nullptr;
		this->right = nullptr;
	}

	Node(int data, Node* left, Node* right) {
		this->data = data;
		this->left = left;
		this->right = right;
	}

	int data;
	Node* left;
	Node* right;
};

Node* find(int x, Node* tree) {
	if (tree == nullptr) {
		return nullptr;
	}
	if (x < tree->data) {
		return find(x, tree->left);
	}
	else if (x > tree->data) {
		return find(x, tree->right);
	}
	else {
		return tree;
	}
}

Node* findMinimum(Node* tree) {
	if (tree == nullptr) {
		return nullptr;
	}
	else if (tree->left == nullptr) {
		return tree;
	}
	else {
		return findMinimum(tree->left);
	}
}

Node* findMaximum(Node* tree) {
	if (tree == nullptr) {
		return nullptr;
	}
	else if (tree->right == nullptr) {
		return tree;
	}
	else {
		return findMaximum(tree->right);
	}
}

Node* findSuccessor(Node* root, int n) {
	if (root == nullptr) {
		return nullptr;
	}

	Node* node = find(n, root);

	if (node->right != nullptr) {
		return findMinimum(node->right);
	}
	//no right sub-tree
	else {
		Node* successor = nullptr;
		Node* ancestor = root;
		while (ancestor != nullptr) {
			if (node->data < ancestor->data) {
				successor = ancestor;
				ancestor = ancestor->left;
			}
			else if(node->data > ancestor->data) {
				ancestor = ancestor->right;
			}
			else {
				break;
			}
		}
		return successor;
	}
}

void insert(Node* node, Node* tree) {
	int val = node->data;
	if (val > tree->data) {
		if (tree->right == nullptr) {
			tree->right = node;
		}
		else {
			insert(node, tree->right);
		}
	}
	else {
		if (tree->left == nullptr) {
			tree->left = node;
		}
		else {
			insert(node, tree->left);
		}
	}
}

void insert(int x, Node* tree) {
	insert(new Node(x), tree);
}

Node* remove(int x, Node* tree) {
	if (tree == nullptr) {
		return nullptr;
	}
	else if (x < tree->data) {
		tree->left = remove(x, tree->left);
	}
	else if (x > tree->data) {
		tree->right = remove(x, tree->right);
	}
	else {
		//has two children
		if (tree->left != nullptr && tree->right != nullptr) {
			Node* succ = findSuccessor(tree, tree->data);
			if (succ == nullptr) return false;
			tree->data = succ->data;
			tree->right = remove(succ->data, tree->right);
		}
		//has one or no child
		else {
			Node* temp = tree;
			tree = (tree->left != nullptr) ? tree->left : tree->right;
			delete temp;
		}
	}
	return tree;
	
}

void inOrderTraversal(Node* root) {
	//left root - visit - right root
	if (root->left != nullptr) {
		inOrderTraversal(root->left);
	}

	std::cout << root->data << " ";

	if (root->right != nullptr) {
		inOrderTraversal(root->right);
	}
}

void preOrderTraversal(Node* root) {
	//visit - left root - right root
	std::cout << root->data << " ";

	if (root->left != nullptr) {
		preOrderTraversal(root->left);
	}

	if (root->right != nullptr) {
		preOrderTraversal(root->right);
	}
}

void postOrderTraversal(Node* root) {
	//left root - right root - visit
	if (root->left != nullptr) {
		inOrderTraversal(root->left);
	}

	if (root->right != nullptr) {
		inOrderTraversal(root->right);
	}

	std::cout << root->data << " ";
}

//testing
int main() {
	Node* root = new Node();
	root->data = 4;
	insert(3, root);
	insert(7, root);
	insert(6, root);
	insert(10, root);
	insert(9, root);

	inOrderTraversal(root);
	
	remove(4, root);
	
	inOrderTraversal(root);
	
	std::cout << std::endl;
	delete root;
	system("PAUSE");
	return 0;
}
