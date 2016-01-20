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

int main() {
	Node* root = new Node();
	root->data = 4;
	insert(new Node(3, nullptr, nullptr), root);
	insert(new Node(7, nullptr, nullptr), root);
	insert(new Node(6, nullptr, nullptr), root);
	insert(new Node(10, nullptr, nullptr), root);
	insert(new Node(9, nullptr, nullptr), root);

	inOrderTraversal(root);
	std::cout << std::endl;
	system("PAUSE");
	return 0;
}
