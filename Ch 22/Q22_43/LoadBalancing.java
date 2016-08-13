package Q22_43;

/**
 * Created by JennaKwon on 5/26/16.
 *
 * Question 22.43
 * Context:
 * n users, unique hash codes h0 to hn-1, m servers.
 * Hash code is ordered by index
 * User i requires b bytes of storage
 * Values k0 to km-2 are used to assign users to servers
 * User with hash code c gets assigned to the server with the lowest ID i, such that c <= ki or to server m - 1 if no such i exists.
 * User-to-server lookup can be done with a BST on m - 1 nodes rather than a hash table.
 * Load on a server = sum of all bytes of storage
 *
 * Question - Compute values for k0 to km-2 that minimizes the load on the most heavily loaded server.
 * (minimize maximum load)
 *
 * What is the recurrence relation if..
 * L(p, q) = maximum load on a server when users with hash codes h0 through hp are assigned to servers 0 through q
 *
 * values of k = min (sum of bytes of storage from users in m_k)
 * minimum variance?
 *
 */
public class LoadBalancing {



}
