#include<iostream>
#include<stack>
#include<queue>

using namespace std;

struct Node {

	int val;
	Node *left;
	Node *right;

	Node(int val, Node *left, Node *right) {
		this->val = val;
		this->left = left;
		this->right = right;
	}
};

void inorderTraversal(Node *root) {
	if(NULL == root) return;
	
	inorderTraversal(root->left);
	cout<<root->val<<" ";
	inorderTraversal(root->right);
}

void preOrderTraversal(Node *root) {

	if(NULL == root) return;
	
	preOrderTraversal(root->left);
	preOrderTraversal(root->right);
	cout<<root->val<<" ";
}

void postOrderTraversal(Node *root) {

	if(NULL == root) return ;
	
	cout<<root->val<<" ";
	postOrderTraversal(root->left);
	postOrderTraversal(root->right);
}

void printInRange(Node *root, int k1, int k2) {
	if(NULL == root) return ;

	if(root->val > k1) {
		printInRange(root->left, k1, k2);
	}
	if(root->val >= k1 && root->val <=k2) {
		cout<<root->val<<" ";
	}
	if(root->val < k2) {
		printInRange(root->right, k1, k2);
	}
}

void printChild(Node *root) {

	if(NULL == root) return;
	cout<<"(";
	if(NULL != root->left) cout<<root->left->val;
	else cout<<"NULL";

	cout<<",";
	
	if(NULL != root->right) cout<<root->right->val;
	else cout<<"NULL";

	cout<<")\n";
}

void printTree(Node *root) {

	if(NULL == root) return ;
	
	cout<<root->val<<"->";
	printChild(root);
	printTree(root->left);
	printTree(root->right);
}

void levelOrderUtil(Node *root, queue<Node*> lQ) {
	
	if(NULL == root) return;

	lQ.push(root);
	
	while(!lQ.empty()) {
		Node *tmp = lQ.front();
		cout<<tmp->val<<" ";
		if(tmp->left != NULL)
			lQ.push(tmp->left);
		
		if(tmp->right != NULL)
			lQ.push(tmp->right);

		lQ.pop();
	}
	
}	

void levelOrderTraversal(Node *root) {

	if(NULL == root) return;
	queue<Node*> lQ;

	levelOrderUtil(root, lQ);
}



int main() {

	Node *one = new Node(1, NULL, NULL);
	Node *four = new Node(4, NULL, NULL);
	Node *three = new Node(3, one, four);
	
	Node *seven = new Node(7, NULL, NULL);
	Node *nine = new Node(9, NULL, NULL);
	Node *eight = new Node(8, seven, nine);

	Node *five = new Node(5, three, eight);

	Node *fifteen = new Node(15, NULL, NULL);
	Node *twentyFive = new Node(25, NULL, NULL);
	Node *twenty = new Node(20, fifteen, twentyFive);
	
	Node *ten = new Node(10, five, twenty);
	
	Node *root = ten;

	cout<<"Tree (Node->(left, right) :\n";
	printTree(root);
	cout<<endl;

	cout<<"Inorder Traversal: ";
	inorderTraversal(root);
	cout<<endl;

	cout<<"Preorder Traversal: ";
	preOrderTraversal(root);
	cout<<endl;
	
	cout<<"Postorder Traversal: ";
	postOrderTraversal(root);
	cout<<endl;

	cout<<"Print in range: ["<<5<<","<<15<<"] :";
	printInRange(root,5, 15);
	cout<<endl;

	cout<<"Level order traversal: ";
	levelOrderTraversal(root);
	cout<<endl;

	return 0;
}
