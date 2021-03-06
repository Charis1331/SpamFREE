package xoulis.xaris.com.spamfree.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/* Firebase */
private const val CHILD_USERS = "users"
private const val CHILD_CODES = "codes"
private const val CHILD_USER_CODES = "user_codes"
const val CHILD_INCOMING_REQUESTS = "incoming_requests"
const val CHILD_OUTGOING_REQUESTS = "outgoing_requests"
const val CHILD_UNCHECKED_OUTGOING_REQUESTS = "unchecked_outgoing_requests"

val uid = { FirebaseAuth.getInstance().uid!! }
val userDisplayName = { FirebaseAuth.getInstance().currentUser!!.displayName!! }

val codesDbRef = FirebaseDatabase.getInstance().reference.child(CHILD_CODES)

val usersDbRef = FirebaseDatabase.getInstance().reference.child(CHILD_USERS)
val userDbRef = { FirebaseDatabase.getInstance().reference.child(CHILD_USERS).child(uid()) }
val userCodesDbRef = { FirebaseDatabase.getInstance().reference.child(CHILD_USER_CODES).child(uid()) }

val incomingRequestsRef =
    { FirebaseDatabase.getInstance().reference.child(CHILD_INCOMING_REQUESTS).child(uid()) }
val outgoingRequestsRef =
    { FirebaseDatabase.getInstance().reference.child(CHILD_OUTGOING_REQUESTS).child(uid()) }
val uncheckedRequestsRef =
    {
        FirebaseDatabase.getInstance().reference.child(CHILD_UNCHECKED_OUTGOING_REQUESTS)
            .child(uid())
    }