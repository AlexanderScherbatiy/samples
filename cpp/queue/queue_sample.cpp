#include <iostream>
#include <queue>
#include <thread>
#include <mutex>
#include <condition_variable>

template <typename T>
class Queue
{
 public:

  void push(const T& item)
  {
    std::unique_lock<std::mutex> mlock(_mutex);
    _queue.push(item);
    mlock.unlock();
    _cond.notify_one();
  }

  void push(T&& item)
  {
    std::unique_lock<std::mutex> mlock(_mutex);
    _queue.push(std::move(item));
    mlock.unlock();
    _cond.notify_one();
  }

  T pop()
  {
    std::unique_lock<std::mutex> mlock(_mutex);
    while (_queue.empty())
    {
      _cond.wait(mlock);
    }
    auto item = _queue.front();
    _queue.pop();
    return item;
  }

  void pop(T& item)
  {
    std::unique_lock<std::mutex> mlock(_mutex);
    while (_queue.empty())
    {
      _cond.wait(mlock);
    }
    item = _queue.front();
    _queue.pop();
  }

 private:
  std::queue<T> _queue;
  std::mutex _mutex;
  std::condition_variable _cond;
};

void produce(Queue<std::string>& queue, int from, int to)
{
  for(int i = from; i <= to; i++)
  {
    std::string item =  "Item-" + std::to_string(i);
    queue.push(std::move(item));
  }
}

void consume(Queue<std::string>& queue, int size)
{
  for(int i = 0; i < size; i++)
  {
    std::string item =  queue.pop();
    std::cout << "consume: " << item << "\n";
  }
}


int main()
{

  Queue<std::string> queue;

  std::thread producer_thread_1(produce, std::ref(queue), 1, 50);
  std::thread producer_thread_2(produce, std::ref(queue), 51, 100);
  std::thread consumer_thread(consume, std::ref(queue), 100);
  producer_thread_1.join();
  producer_thread_2.join();
  consumer_thread.join();

  return 0;
}
