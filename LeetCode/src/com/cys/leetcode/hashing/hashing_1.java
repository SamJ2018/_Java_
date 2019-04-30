package com.cys.leetcode.hashing;

/**
 * hash table  collision solution:
 *      linear probing and separate chain
 *
 * use hashcode and array length to get index of array
 *    separate chain  stored like linked list (java implementation)
 *
 *  rehashing when number of elements is growing faster(load factor)
 *
 *  hash table
 *      near O(1) search time complexity
 *      near O(1) to add a new element
 *
 *   hashtable (legacy api of java)
 *      synchronized/thread-safe   don't allow null keys
 *   hashmap
 *      non thread-safe/better performance
 *      allows one null key and any number of null values
 *      LinkedHashMap/concurrentHashMap
 *
 *
 *  c. HashMap(prior to java7) separate chain hashtable implementation
 *  in java8  hashmap has some performance improvement
 */
public class hashing_1 {

}
