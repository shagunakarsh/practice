#include<iostream>
using namespace std;

struct Node {
	string value;
	Node *index[26];
};

Node* getNewNode() {
	Node *node = new Node();
	for(int i=0;i<26; ++i) {
		(node->index)[i] = NULL;
	}
	return node;
}

class Trie {

	public:

	Node *root;

	void insertWord(string word) {
		Node *tmp = root;
		int length = word.length();
		for(int i=0; i<length; ++i) {
			int ind = word[i]-'a';
			cout<<"index: "<<ind<<endl;
			if( (tmp->index)[ind] != NULL ) {
				tmp = (tmp->index)[ind];
			} else {
				(tmp->index)[ind] = getNewNode();
				tmp = (tmp->index)[ind];
			}
		}
		if((tmp->value).empty()) {
			cout<<"setting word: "<<word<<endl;
			tmp->value=word;
		}
	}

	bool searchWord(string word) {
		if(word.empty()) return false;
		int length = word.length();
		Node *tmp = root;

		for(int i=0;i<length; ++i) {
			int ind = word[i]-'a';
			if( (tmp->index)[ind] != NULL ) {
				cout<<(char)(ind + 'a')<<endl;
				tmp = (tmp->index)[ind];
			} else {
				cout<<"Not found : "<<word<<endl;
				return false;
			}
		}

		cout<<"value found: "<<tmp->value<<endl;
		return true;
	}

	Trie(Node *root) {
		this->root = root;
	}
};

int main() {

	Node *root = getNewNode();
	
	Trie *trie = new Trie(root);
	cout<<"hello"<<endl;
	trie->insertWord("hello");
	cout<<"game"<<endl;
	trie->insertWord("game");
	cout<<"gamer"<<endl;
	trie->insertWord("gamer");

	cout<<"search: game"<<endl;
	trie->searchWord("game");
	cout<<"search: lol"<<endl;
	trie->searchWord("lol");
	return 0;
}
