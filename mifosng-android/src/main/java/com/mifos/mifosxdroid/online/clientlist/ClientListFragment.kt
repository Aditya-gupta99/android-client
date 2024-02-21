/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.mifosxdroid.online.clientlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.mifos.feature.client.clientList.presentation.ClientListScreen
import com.mifos.mifosxdroid.R
import com.mifos.mifosxdroid.activity.home.HomeActivity
import com.mifos.mifosxdroid.core.MifosBaseFragment
import com.mifos.objects.client.Client
import com.mifos.objects.navigation.ClientArgs
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by ishankhanna on 09/02/14.
 *
 **/

@AndroidEntryPoint
class ClientListFragment : MifosBaseFragment() {

    private val arg: ClientListFragmentArgs by navArgs()
    private lateinit var clientList: List<Client>
    private var isParentFragment = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            clientList = arg.clientListArgs.clientsList
            isParentFragment = arg.clientListArgs.isParentFragment
        }

        if (!isParentFragment) (activity as HomeActivity).supportActionBar?.title =
            getString(R.string.clients)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ClientListScreen(createNewClient = {
                    if (!isParentFragment) {
                        findNavController().navigate(R.id.action_navigation_client_list_to_createNewClientFragment)
                    } else {
                        findNavController().navigate(R.id.action_clientListFragment_to_createNewClientFragment)
                    }
                }, syncClicked = { clientList ->
                    //Todo Store client data to realm in next Pull request

                }, onClientSelect = { client ->

                    val action =
                        ClientListFragmentDirections.actionClientListFragmentToClientActivity(
                            ClientArgs(clientId = client.id)
                        )
                    findNavController().navigate(action)
                })
            }
        }
    }
}