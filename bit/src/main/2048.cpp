
#include <iostream>
#include <vector>
#include <algorithm>
#include <stdlib.h>
#include <time.h>

using namespace std;

class Matrix
{

	// constants
	static const int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;
	static const int END_SCORE = 16;

	// state vars
	vector< vector<int> > matrix;
	int Rows, Cols;

	// for randomly choosing items
	struct Element
	{
		int i;
		int j;
		Element(int i = 0, int j = 0) : i(i), j(j) {}
	};

  public:
	Matrix(const int rows = 4, const int cols = 4)
	{

		Rows = rows;
		Cols = cols;

		for (int i = 0; i < rows; ++i)
		{
			vector<int> array;
			for (int j = 0; j < cols; ++j)
			{
				array.push_back(0);
			}
			matrix.push_back(array);
		}
		// testing initial state
		//matrix[1][1] = 1;
		//matrix[3][1] = 1;
		//matrix[3][3] = 4;
		//matrix[0][0] = 4;
	}

	void print()
	{
		for (int i = 0; i < Rows; ++i)
		{
			for (int j = 0; j < Cols; ++j)
			{
				cout << matrix[i][j] << ' ';
			}
			cout << endl;
		}
		cout << endl;
	}

	void mergeUp()
	{
		for (int j = 0; j < Cols; ++j)
		{
			vector<int> stack;
			bool skip = false;
			for (int i = 0; i < Rows; ++i)
			{

				if (matrix[i][j] != 0)
				{
					stack.push_back(matrix[i][j]);

					if (!skip &&
						stack.size() > 1 &&
						stack[stack.size() - 1] == stack[stack.size() - 2])
					{
						int item = stack.back();
						stack.pop_back();
						stack.pop_back();
						stack.push_back(item * 2);
						skip = true;
					}
				}
			}
			stack.resize(Rows);
			for (int i = 0; i < Rows; ++i)
				matrix[i][j] = stack[i];
		}
	}

	void mergeLeft()
	{

		for (int i = 0; i < Rows; ++i)
		{
			vector<int> stack;
			bool skip = false;
			for (int j = 0; j < Cols; ++j)
			{

				if (matrix[i][j] != 0)
				{
					stack.push_back(matrix[i][j]);

					if (!skip &&
						stack.size() > 1 &&
						stack[stack.size() - 1] == stack[stack.size() - 2])
					{
						int item = stack.back();
						stack.pop_back();
						stack.pop_back();
						stack.push_back(item * 2);
						skip = true;
					}
				}
			}
			stack.resize(Rows);
			for (int j = 0; j < Cols; ++j)
				matrix[i][j] = stack[j];
		}
	}

	void mergeRight()
	{
		for (int i = 0; i < Rows; ++i)
		{
			vector<int> stack;
			bool skip = false;

			for (int j = Cols - 1; j >= 0; --j)
			{

				if (matrix[i][j] != 0)
				{
					stack.push_back(matrix[i][j]);

					if (!skip &&
						stack.size() > 1 &&
						stack[stack.size() - 1] == stack[stack.size() - 2])
					{
						int item = stack.back();
						stack.pop_back();
						stack.pop_back();
						stack.push_back(item * 2);
						skip = true;
					}
				}
			}

			int j = Cols - 1;
			reverse(stack.begin(), stack.end());
			while (stack.size())
			{
				int item = stack.back();
				stack.pop_back();
				matrix[i][j] = item;
				j--;
			}

			while (j >= 0)
			{
				matrix[i][j] = 0;
				j--;
			}
		}
	}

	void mergeDown()
	{
		for (int j = 0; j < Cols; ++j)
		{
			vector<int> stack;
			bool skip = false;
			for (int i = Rows - 1; i >= 0; --i)
			{

				if (matrix[i][j] != 0)
				{
					stack.push_back(matrix[i][j]);

					if (!skip &&
						stack.size() > 1 &&
						stack[stack.size() - 1] == stack[stack.size() - 2])
					{
						int item = stack.back();
						stack.pop_back();
						stack.pop_back();
						stack.push_back(item * 2);
						skip = true;
					}
				}
			}

			int i = Rows - 1;
			reverse(stack.begin(), stack.end());

			while (stack.size())
			{
				int item = stack.back();
				stack.pop_back();
				matrix[i][j] = item;
				i--;
			}
			while (i >= 0)
			{
				matrix[i][j] = 0;
				i--;
			}
		}
	}

	void Move(int operation)
	{
		switch (operation)
		{
		default:
			cout << "Invalid Operation" << endl;
			break;
		case UP:
			cout << "UP" << endl;
			mergeUp();
			break;
		case LEFT:
			cout << "LEFT" << endl;
			mergeLeft();
			break;
		case DOWN:
			cout << "DOWN" << endl;
			mergeDown();
			break;
		case RIGHT:
			cout << "RIGHT" << endl;
			mergeRight();
			break;
		}
	}

	// return true if any cell contains endscore
	bool winner()
	{
		for (int i = 0; i < Rows; ++i)
		{
			for (int j = 0; j < Cols; ++j)
			{
				if (matrix[i][j] >= END_SCORE)
					return true;
			}
		}
		return false;
	}

	// return true if no adjacent elements have same value
	bool noMoves()
	{

		for (int i = 0; i < Rows; ++i)
		{
			for (int j = 0; j < Cols; ++j)
			{

				if (matrix[i][j] == 0)
					return false;
				if (j - 1 >= 0 &&
					matrix[i][j] == matrix[i][j - 1])
					return false;

				if (i - 1 >= 0 &&
					matrix[i][j] == matrix[i - 1][j])
					return false;
			}
		}
		return true;
	}

	bool insertRandom()
	{
		// stores avaliable items for inserting
		vector<Element> array;

		for (int i = 0; i < Rows; ++i)
		{
			for (int j = 0; j < Cols; ++j)
			{

				if (matrix[i][j] == 0)
				{
					array.push_back(Element(i, j));
				}
			}
		}

		// no place to insert :(
		if (array.size() == 0)
			return false;

		Element e = array[rand() % array.size()];
		matrix[e.i][e.j] = 2;
		cout << "inserted at " << e.i << ',' << e.j << endl;
		return true;
	}
};
/*
Matrix::UP = 0;
Matrix::LEFT = 1;
Matrix::DOWN = 2;
Matrix::RIGHT = 3;
*/
int main()
{

	srand(time(NULL));

	Matrix M(4, 4);
	M.print();

	while (!M.winner() && !M.noMoves())
	{
		int move;
		cin >> move;

		M.Move(move);
		if (!M.insertRandom())
		{
			cout << "Unable to insert new element!!\n Try another move" << endl;
		}
		M.print();
	}

	if (M.winner())
		cout << "You have won!!" << endl;
	else if (M.noMoves())
		cout << "You lost !!" << endl;

	return 0;
}
