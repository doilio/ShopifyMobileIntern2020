package com.doilio.shopifymemory.fragments


import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doilio.shopifymemory.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import java.lang.String
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_about, container, false)
        val versionElement = Element()
        versionElement.setTitle(getString(R.string.versao) + getString(R.string.versao_nr))

        return AboutPage(context)
            .isRTL(false)
            .addItem(versionElement)
            .setDescription(getString(R.string.about_descricao))
            .addGroup(getString(R.string.about_fale_conosco))
            .addEmail(getString(R.string.dev_email), getString(R.string.envie_email))
            .addWebsite(getString(R.string.dev_website), getString(R.string.acesse_nosso_site))
            .addGroup(getString(R.string.redes_sociais))
            .addFacebook(getString(R.string.dev_facebook_page), getString(R.string.facebook))
            .addGitHub(getString(R.string.dev_github_page), getString(R.string.github))
            .addInstagram(getString(R.string.dev_instagram_page), getString(R.string.instagram))
            .addItem(createCopyright())
            .create()

    }

    private fun createCopyright(): Element? {
        val copyright = Element()
        val copyrightString = String.format(
            "Copyright %d by ShopiMemory", Calendar.getInstance()
                .get(Calendar.YEAR)
        )
        copyright.title = copyrightString
        copyright.iconDrawable = R.mipmap.ic_launcher
        copyright.gravity = Gravity.CENTER
        return copyright
    }


}
