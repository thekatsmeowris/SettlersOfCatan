


echo "# DynamicArray" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin git@github.com:thekatsmeowris/DynamicArray.git
git push -u origin master
#include "DynamicIntArray.h"


namespace cs20a
{


	DynamicIntArray::DynamicIntArray()
	{
		used=0;
		capacity=0;
		elements=nullptr;

	}


DynamicIntArray::DynamicIntArray(int size)
{
	used=1;
	capacity=size;
	elements=new int[capacity];

}

DynamicIntArray:: DynamicIntArray(const DynamicIntArray &d)
{
	capacity=d.capacity;
	used=d.used;
	elements=new int [capacity];
	for(int i=0; i<used; i++)
	{
		elements[i]=d.elements[i];


	}

}

DynamicIntArray::~DynamicIntArray()
{
	delete [] elements;
	elements=nullptr;
}


DynamicIntArray&DynamicIntArray::operator= (const DynamicIntArray & rhs)
{
	int * new_elements;
	if(this==&rhs)
	{
		return *this;
	}
	if(this != &rhs)
	{
		int new_used, new_capacity, i;
		new_used=used;
		new_used=rhs.used;
		new_capacity=rhs.capacity;
		new_elements=new int[new_capacity];
		for(i=0; i<new_used; i++)
		{
			new_elements[i]=rhs.elements[i];



		}
		delete [] elements;
		new_elements=elements;

	}
return *this;
}
bool DynamicIntArray::isEmpty() const
{
	if(used==0)
	{
		return true;
	}
	else
		return false;

}
int DynamicIntArray::getUsed() const
{

	return used;
}
int DynamicIntArray::getCapacity() const
{


	return capacity;
}
void DynamicIntArray::add(int element)
{

	int*temp=new int[capacity+1];
	for(int i=0; i<capacity; i++)
	{
		temp[i]=elements[i];




	}




	delete [] elements;
	elements=temp;
	temp=NULL;
	elements[capacity-1]=element;
	capacity++;




}
void DynamicIntArray::insert(int i, int element)
{
	int x;

		if(used==capacity)
		{
			this->expandCapacity();
		}
			for(x=capacity-1; x>=i; --x)
			{
				elements[x+1]=elements[x];


			}
			elements[x]=element;








}
int DynamicIntArray::remove(int i)
{

	for(int x=0; x<capacity; x++)
	{
		if (elements[x]==i)
		{
			for ( ; x<capacity-x; x++)
			{
				elements[x]=elements[x+1];

			}
			elements[capacity-1]=0;

		}
	}

return *elements;
}

void DynamicIntArray::clear()
{
	if (capacity>0)
	{
		delete [] elements;
		used =0;
		capacity=0;
		elements=new int[capacity];
	}
}
int& DynamicIntArray::operator[] (int i)
{

	return elements[i];
}
std::ostream& operator << (std::ostream& outs, DynamicIntArray& d)
{
for (int i=0; d.getUsed(); i++)
{
	outs<<d[i]<<"";
}
	return outs;
}
bool operator==(const DynamicIntArray&d1, const DynamicIntArray&d2)
		{
	if(d1.getCapacity()!=d2.getCapacity())
	return false;
	if(d1.getUsed()!=d2.getUsed())
		return false;
	else
		return true;
	}

void DynamicIntArray::expandCapacity()
{
	capacity=capacity*2;
	int *temp=new int[capacity];
	for(int i=0; i<capacity; i++)
	{
		temp[i]=elements[i];
	}
	delete [] elements;
	temp=elements;
}
}
