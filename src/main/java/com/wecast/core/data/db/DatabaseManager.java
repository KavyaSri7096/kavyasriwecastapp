package com.wecast.core.data.db;

import com.wecast.core.data.db.dao.ChannelDao;
import com.wecast.core.data.db.dao.ChannelGenreDao;
import com.wecast.core.data.db.dao.ComposerDao;
import com.wecast.core.data.db.dao.HighlightedDao;
import com.wecast.core.data.db.dao.ReminderDao;
import com.wecast.core.data.db.dao.TVGuideDao;
import com.wecast.core.data.db.dao.TVShowDao;
import com.wecast.core.data.db.dao.TVShowGenreDao;
import com.wecast.core.data.db.dao.VodDao;
import com.wecast.core.data.db.dao.VodGenreDao;

import javax.inject.Inject;

/**
 * Created by ageech@live.com
 */

public class DatabaseManager {

    private final ComposerDao composerDao;
    private final HighlightedDao highlightedDao;
    private final ChannelDao channelDao;
    private final ChannelGenreDao channelGenreDao;
    private final TVGuideDao tvGuideDao;
    private final VodDao vodDao;
    private final VodGenreDao vodGenreDao;
    private final TVShowDao tvShowDao;
    private final TVShowGenreDao tvShowGenreDao;
    private final ReminderDao reminderDao;

    @Inject
    public DatabaseManager(ComposerDao composerDao, HighlightedDao highlightedDao, ChannelDao channelDao, ChannelGenreDao channelGenreDao, TVGuideDao tvGuideDao,
                           VodDao vodDao, VodGenreDao vodGenreDao, TVShowDao tvShowDao, TVShowGenreDao tvShowGenreDao, ReminderDao reminderDao) {
        this.composerDao = composerDao;
        this.highlightedDao = highlightedDao;
        this.channelDao = channelDao;
        this.channelGenreDao = channelGenreDao;
        this.tvGuideDao = tvGuideDao;
        this.vodDao = vodDao;
        this.vodGenreDao = vodGenreDao;
        this.tvShowDao = tvShowDao;
        this.tvShowGenreDao = tvShowGenreDao;
        this.reminderDao = reminderDao;
    }

    public void clear() {
        // Do not remove composer data because
        // we will need it in case if user do logout
        //composerDao.clear();
        highlightedDao.clear();
        channelDao.clear();
        channelGenreDao.clear();
        tvGuideDao.clear();
        vodDao.clear();
        vodGenreDao.clear();
        tvShowDao.clear();
        tvShowGenreDao.clear();
        reminderDao.clear();
    }
}
