#include <iostream>
#include <queue>
#include <thread>
#include <mutex>
#include <condition_variable>

#include <unistd.h>

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

  bool pop(T& item)
  {
    std::unique_lock<std::mutex> mlock(_mutex);

    while (_queue.empty())
    {
      if(_closed) return false;
      _cond.wait(mlock);
    }

    item = _queue.front();
    _queue.pop();

    return true;
  }

  void close()
  {
    std::unique_lock<std::mutex> mlock(_mutex);
    _closed = true;
    mlock.unlock();
    _cond.notify_all();
  }

 private:
  std::queue<T> _queue;
  std::mutex _mutex;
  std::condition_variable _cond;
  bool _closed = false;
};

void produce(Queue<std::string>& queue, int from, int to)
{
  for(int i = from; i <= to; i++)
  {
    std::string item =  "Item-" + std::to_string(i);
    queue.push(std::move(item));
  }
  usleep(20000);
  queue.close();
}

void consume(Queue<std::string>& queue)
{
  std::string item;
  while(queue.pop(item)) {
    std::cout << "consume: " << item << "\n";
  }
}

int main()
{
  Queue<std::string> queue;

  std::thread producer_thread(produce, std::ref(queue), 1, 20);
  std::thread consumer_thread(consume, std::ref(queue));
  producer_thread.join();
  consumer_thread.join();

  return 0;
}
